package wasdev.sample.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.Message;
import com.restfb.types.send.SendResponse;
import com.restfb.types.webhook.WebhookEntry;
import com.restfb.types.webhook.WebhookObject;
import com.restfb.types.webhook.messaging.MessagingItem;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    
	    //protected String client_id = "Rpxe0QzxsUFDsZW7zqWd1VrA";
	   // protected String client_secret = "5d14afd3ab9a3e4ed0d0749b2c13c855";

	    
	    
	 /*  
	    protected String conv_bluemix_username = "6fa4fac3-4c0b-40b4-9ce5-f13654916155";
	    protected String conv_bluemix_password = "PfqWtMqCPpz3";
	    protected String conv_bluemix_id = "7e0224b5-b107-497f-84d6-881bc33261ab";

	    public SimpleServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	response.getWriter().append("Hi");
//		}
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
	/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String category = request.getParameter("category");
			String payload = request.getParameter("payload");
			
			JSONObject result = new JSONObject();
			result.put("code", 200);
			result.put("message", "This is service response from IBM Blockchain ("+category+").");
			
			response.getWriter().append(result.toString());

			System.out.println("### Payload received from Blockchain ###");
			System.out.println(payload);
			System.out.println("### /Payload received from Blockchain ###");
		}
	    }
	    
	    
	    */
	    
	  
	   
	    private String FbToken = "EAAawiZB0ZAUZBIBACdZAKZCvp1zuoPALGYWiFJWpGRO6mNZADxuUQ5CPe4MYAf3MurnGHQaHhUDZC3HHb4B4PbadEjYmMdHifXSuLnMfAkwqcZAZBJ1yEFZCe4tbDFTGB0B3gpNHG1HLYwrrUPqUSJoLpUJ1abWrkJxmwZAH0PCMLc5PoxpsCtG6ZBFY";
		private String verifyToken = "StcPoc";
		
		static BufferedReader br = null;
		static MessageResponse response = null;
		static Map context = new HashMap();
		static String userName ;
		
		static JSONObject finObject;
		static JSONArray sportsArray;
		static int count = 0 ;
		static String date;
		static String fullName;
		static String lastName;
		static String firstName;
		static String newName;
		static String newLastName;
		static String number;
		static String newNumber;
		static String email;
		static String emailSplits[];
		
		static Scanner sa = new Scanner(System.in);
		
		
	    /**
	     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	     */
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	    	
	    	
	    	     response.setContentType("text/plain");
	        //response.getWriter().print("Hello World!");
	        
	        String hubToken = request.getParameter("hub.verify_token");
			String hubChallenge = request.getParameter("hub.challenge");
			
			if (verifyToken.equals(hubToken)) {
				response.getWriter().write(hubChallenge);
				response.getWriter().flush();
				response.getWriter().close();
			}
			else {
				response.getWriter().write("incorrectTOken");
			}
			
		}
		
	    

	    
	    
	    
	    
	    
	  
	    
	    //==========
	    
	    
	    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//=====
		StringBuffer sb = new StringBuffer();
		BufferedReader br = request.getReader();
		String line = "";
		while ((line = br.readLine())!=null) {
			sb.append(line);
		}
		
		JsonMapper mapper = new DefaultJsonMapper();
		WebhookObject webhookObj = mapper.toJavaObject(sb.toString(), WebhookObject.class);
		
		for (WebhookEntry entry: webhookObj.getEntryList()) {
			if(entry.getMessaging()!=null) {
				for(MessagingItem mItem: entry.getMessaging()) {
				
					String senderId = mItem.getSender().getId();
					IdMessageRecipient recipient = new IdMessageRecipient(senderId);
					
					
					
					if(mItem.getMessage()!=null && mItem.getMessage().getText()!=null) {
						
						SendMessage(recipient,new Message("Hi"));
						
					}
					
				}
			}
		}
	}
	public void SendMessage(IdMessageRecipient recipient ,Message message) {
		
		FacebookClient pageClient = new DefaultFacebookClient(FbToken, Version.VERSION_2_6);

		SendResponse resp = pageClient.publish("me/messages", SendResponse.class,
		     Parameter.with("recipient", recipient), // the id or phone recipient
			 Parameter.with("message", message));
		
		   
		
	}

}


