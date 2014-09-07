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
package de.mare.mobile.ws;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Simple Chat service
 * 
 * @author mreinhardt
 * 
 */
@ServerEndpoint("/message")
public class MessageService {
	// Create a Set to hold client sessions
	private static final Set<Session> clientSessions = Collections
	    .synchronizedSet(new HashSet<Session>());

	/**
	 * Add client session to the Set
	 * 
	 * @param pSession
	 */
	@OnOpen
	public void onOpen(Session pSession,
	    @PathParam("userid") String pUserID) {
		pSession.getUserProperties().put(pUserID, true);
		clientSessions.add(pSession);
	}

	/**
	 * Remove client session from the Set
	 * 
	 * @param pSession
	 */
	@OnClose
	public void onClose(Session pSession) {
		clientSessions.remove(pSession);
	}

	/**
	 * Send Text back
	 * 
	 * @param message
	 * @param client
	 * @throws IOException
	 * @throws EncodeException
	 */
	@OnMessage
	public void onMessage(String message, Session client) throws IOException,
	    EncodeException {

		// send data to all connected clients (including caller)
		for (Session clientSession : clientSessions) {
			// if (message.equals("Open Sesame")) {
			//
			// JsonObjectBuilder builder = Json.createObjectBuilder();
			// builder.add("person",
			// Json.createObjectBuilder().add("firstName", "Michael")
			// .add("lastName", "Jo"));
			// JsonObject result = builder.build();
			// // StringWriter sw = new StringWriter();
			// // try(JsonWriter writer = Json.createWriter(sw))
			// // {
			// // writer.writeObject(result);
			// // }
			// //
			// message = result.toString();
			//
			// }
			clientSession.getBasicRemote().sendText(message);

		}
	}

	@OnError
	public void onError(Session aclientSession, Throwable aThrowable) {
		System.out.println("Error : " + aclientSession);

	}
}
