package c2

import org.jcsp.lang.*

class Ex06LTS implements CSProcess
{
	def ChannelInput inChannel
	def ChannelOutput outChannel
	def debugList = []
	
	void run (){
		def inList = inChannel.read()
		while (inList[0] != -1)
		{
			// hint: output	list elements as single integers
			for(j in 0 ..< inList.size)
			{
				outChannel.write(inList[j])
				debugList = debugList << inList[j]
			}
			inList = inChannel.read()
		}
		outChannel.write(-1)
	}
}

