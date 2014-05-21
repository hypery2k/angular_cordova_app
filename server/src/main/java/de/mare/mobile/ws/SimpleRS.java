/**
 * File: SimpleRS.java 21.05.2014, 11:52:40, author: mreinhardt
 * 
 * Project: smartKITA
 * 
 * PROFI Engineering Systems AG
 * Otto-Röhm-Str. 18
 * 64293 Darmstadt
 * http://www.profi-ag.de
 */
package de.mare.mobile.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * @author mreinhardt
 * 
 */
@Path("/simple")
public class SimpleRS {

  @GET
  @Produces("text/plain")
  public String halloText(@QueryParam("name") String name) {
    return "Hallo " + name;
  }

  @GET
  @Produces("text/html")
  public String halloHtml(@QueryParam("name") String name) {
    return "<html><title>HelloWorld</title><body><h2>Html: Hallo " + name + "</h2></body></html>";
  }

}
