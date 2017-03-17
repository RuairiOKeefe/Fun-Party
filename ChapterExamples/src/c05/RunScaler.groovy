package c05
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.groovy.plugAndPlay.*


def data = Channel.one2one()
def timedData = Channel.one2one()
def scaledData = Channel.one2one()
def oldScale = Channel.one2one()
def newScale = Channel.one2one()
def pause = Channel.one2one() 

def network = [ new GNumbers ( outChannel: data.out() ),
                new GFixedDelay ( delay: 1000, 
                		          inChannel: data.in(), 
                		          outChannel: timedData.out() ),
							  
                new Scale ( inChannel: timedData.in(),
                            outChannel: scaledData.out(),
                            factor: oldScale.out(),
                            suspend: pause.in(),
                            injector: newScale.in(),
                            multiplier: 2,
                            scaling: 2 ),
						
                new UIController (oldScale: oldScale.in(),
								 data: scaledData.in(),
                                 pause: pause.out(),
                                 newScale: newScale.out() )
              ]

new PAR ( network ).run()                                                            
