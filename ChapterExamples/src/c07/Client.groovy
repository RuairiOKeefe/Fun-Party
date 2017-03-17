package c07

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*


class Client implements CSProcess{  
	
  def ChannelInput receiveChannel
  def ChannelOutput requestChannel
  def clientNumber   
  def selectList = [ ] 
   
  void run () {
    def iterations = selectList.size
    println "Client $clientNumber has $iterations values in $selectList"
    for ( i in 0 ..< iterations)
	{
      def key = selectList[i]
	  def expected = key*10
	  def valuesMatch = false
	  
      requestChannel.write(key)
      def v = receiveChannel.read()
	  if(v==expected)
	  	valuesMatch = true
		println("Client $clientNumber requested value at location $key. Expected matches Actual: $valuesMatch")
    }
	
    println "Client $clientNumber has finished"
  }
}
