package c07

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*


class Server implements CSProcess{
	  
  def ChannelInput clientRequest
  def ChannelOutput clientSend  
  def ChannelOutput thisServerRequest
  def ChannelInput thisServerReceive  
  def ChannelInput otherServerRequest
  def ChannelOutput otherServerSend  
  def dataMap = [ : ]    
  def serverNumber
                
  void run () {
    def CLIENT = 0
    def OTHER_REQUEST = 1
    def THIS_RECEIVE = 2
    def serverAlt = new ALT ([clientRequest, 
		                      otherServerRequest, 
							  thisServerReceive])
    while (true) {
      def index = serverAlt.select()
	  
      switch (index) {		  
        case CLIENT :
          def key = clientRequest.read()
		  //println("Client: $serverNumber looking for Key: $key in Server: $serverNumber")
          if ( dataMap.containsKey(key) )
		  {
            clientSend.write(dataMap[key])    
			//println("Server: $serverNumber is sending Key: $key to Client: $serverNumber")
		  } 
          else 
            thisServerRequest.write(key)
          //end if 
          break
        case OTHER_REQUEST :
          def key = otherServerRequest.read()
		  //println("Server: " + (1 + (-serverNumber)) + " looking for Key: $key in Server: $serverNumber")
          if ( dataMap.containsKey(key) ) 
            otherServerSend.write(dataMap[key])          
          else 
            otherServerSend.write(-1)
          //end if 
          break
        case THIS_RECEIVE :
          clientSend.write(thisServerReceive.read() )
		  //println("Server: $serverNumber has recieved value from Server: "+ (1 + (-serverNumber))+"")
          break
      } // end switch              
    } //end while   
  } //end run
}
