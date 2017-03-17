package c09

import org.jcsp.lang.*
import org.jcsp.groovy.*

class AccuracyTest implements CSProcess
{
	def ChannelInput inChannel
	def ChannelOutput outChannel
	
	void run()
	{
		def e = inChannel.read()
		def prev = 0
		while(true)
		{
			def expected = (e.data - prev) - 1
			if(e.missed == expected)
			{
				e.valid = true
			}
			prev = e.data
			
			outChannel.write(e)
			e = inChannel.read()
		}			
	}
}
