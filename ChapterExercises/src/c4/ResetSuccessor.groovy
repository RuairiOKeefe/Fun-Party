package c4


import org.jcsp.lang.*
import org.jcsp.groovy.*

class ResetSuccessor implements CSProcess {
	  
  def ChannelOutput outChannel
  def ChannelInput  inChannel
  def ChannelInput  resetChannel
	  
  void run () {
    def guards = [ resetChannel, inChannel  ]
    def alt = new ALT ( guards )
	while (true)
	{
	  // deal with inputs from resteChannel and inChannel
	  // use a priSelect
		def index = alt.priSelect()
		if (index == 0 )
		{
			inChannel.read()
			def resetValue = resetChannel.read()
			outChannel.write(resetValue)
		}
		else
		{
		  outChannel.write(inChannel.read()+1)
		}
	}
  }
}
