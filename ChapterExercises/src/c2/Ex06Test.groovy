package c2

import org.jcsp.lang.*
import org.jcsp.groovy.*


class Ex06Test extends GroovyTestCase
{	
	void test()
	{
			
		One2OneChannel connect1 = Channel.createOne2One()
		One2OneChannel connect2 = Channel.createOne2One()
	
		def GenerateSetsOfThree = new GenerateSetsOfThree ( outChannel: connect1.out() )
	
		def Ex06LTS = new Ex06LTS ( inChannel: connect1.in(), outChannel: connect2.out() )
	
		def CreateSetsOfEight = new CreateSetsOfEight ( inChannel: connect2.in() )
	
		def processList = [ GenerateSetsOfThree, Ex06LTS, CreateSetsOfEight ]
			
		new PAR (processList).run()
		def expected = Ex06LTS.debugList.subList( Ex06LTS.debugList.size - 8, Ex06LTS.debugList.size )
	
		def actual = CreateSetsOfEight.outList
		assertTrue( actual == expected)
	}
}
