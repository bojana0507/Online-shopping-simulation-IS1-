package aplikacija;

import com.google.gson.Gson;
import javax.json.JsonString;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("test")
public class Test {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String ping(){
        String json = new Gson().toJson("radii");
        return json;
    }
}
