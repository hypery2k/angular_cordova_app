/**
 * Cordova Angular JE22 Demo App
 *
 * File: MessageService.java, 18.07.2014, 12:49:55, mreinhardt
 *
 * https://www.martinreinhardt-online.de/apps
 *
 * @project https://github.com/hypery2k/angular_cordova_app
 *
 * @copyright 2014 Martin Reinhardt contact@martinreinhardt-online.de
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package de.mare.mobile.api.ws;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple Chat service
 * 
 * @author mreinhardt
 * 
 */
@ServerEndpoint("/message/{username}")
public class MessageService {

	private Logger LOG = LoggerFactory.getLogger(MessageService.class);

	public static final String PROP_USERNAME = "username";

	public static final String MSG_PROP_TEXT = "text";

	public static final String MSG_PROP_TO = "to";

	public static final String MSG_PROP_FROM = "from";

	// Create a Set to hold client sessions
	private static final Set<Session> clientSessions = Collections
	    .synchronizedSet(new HashSet<Session>());

	/**
	 * Add client session to the Set
	 * 
	 * @param pSession
	 */
	@OnOpen
	public void onOpen(final Session pClientSession,
	    @PathParam("username") String pUsername) {
		pClientSession.getUserProperties().put(PROP_USERNAME, pUsername);
		clientSessions.add(pClientSession);
	}

	/**
	 * Remove client session from the Set on close
	 * 
	 * @param pClientSession
	 */
	@OnClose
	public void onClose(final Session pClientSession) {
		clientSessions.remove(pClientSession);
	}

	@OnMessage
	public void onMessage(final String message, final Session pClientSession) throws IOException,
	    EncodeException {
		final JsonObject jsonMsg = getJsonFromString(message);
		final String msgText = jsonMsg.getString(MSG_PROP_TEXT);
		final String msgRecipient = jsonMsg.getString(MSG_PROP_TO);
		final String msgSender = jsonMsg.getString(MSG_PROP_FROM);
		// send data to all connected clients (including caller)
		for (Session clientSession : clientSessions) {
			String currentRecipient = (String) clientSession.getUserProperties().get(PROP_USERNAME);
			if (currentRecipient.equalsIgnoreCase(msgRecipient)) {
				String msg = Json.createObjectBuilder().add(MSG_PROP_TEXT, msgText)
				    .add(MSG_PROP_TO, currentRecipient).add(MSG_PROP_FROM, msgSender).build().toString();
				clientSession.getBasicRemote().sendText(msg);
			}
			if (currentRecipient.equalsIgnoreCase(msgSender)) {
				String msg = Json.createObjectBuilder().add(MSG_PROP_TEXT, msgText)
				    .add(MSG_PROP_TO, msgRecipient).add(MSG_PROP_FROM, msgSender).build().toString();
				clientSession.getBasicRemote().sendText(msg);
			}
		}
	}

	@OnError
	public void onError(final Session pClientSession, final Throwable pThrowable) {
		final String currentRecipient = (String) pClientSession.getUserProperties().get(PROP_USERNAME);
		LOG.error("Error on message service for user " + currentRecipient, pThrowable);
	}

	// HELPERS

	/**
	 * Convert JSON string to a JSON object using standard JEE methods
	 * 
	 * @param pJSON
	 *          JSON string to parse
	 * @return JSON object
	 */
	private JsonObject getJsonFromString(final String pJSON) {
		final Reader sReader = new StringReader(pJSON);
		final JsonReader jReader = Json.createReader(sReader);
		final JsonObject result = jReader.readObject();
		jReader.close();
		try {
			sReader.close();
		} catch (IOException e) {
			LOG.error("Unkown IO error during parsing JSON from String.", e);
		}
		return result;
	}
}
