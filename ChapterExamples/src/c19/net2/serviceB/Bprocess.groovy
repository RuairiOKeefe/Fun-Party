package c19.net2.serviceB

import org.jcsp.lang.*
import org.jcsp.net2.*;
import org.jcsp.net2.tcpip.*
import org.jcsp.groovy.*
import java.awt.*
import org.jcsp.awt.*

class Bprocess implements CSProcess, Serializable{
	
	void run(){
		def root = new ActiveClosingFrame ("SERVICE B")
		def main = root.getActiveFrame()
		def sorrylabel = new Label("This service is not yet available")
		main.setLayout(new BorderLayout())
		main.setSize(480, 640)
		main.add(sorrylabel)
		main.pack()
		main.setVisible(true)
		new PAR([root]).run()

	}
}
