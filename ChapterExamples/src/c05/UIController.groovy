package c05

import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.awt.*
import java.awt.*

class UIController implements CSProcess
{
	def ChannelInput oldScale
	def ChannelInput data
	def ChannelOutput pause
	def ChannelOutput newScale

	void run()
	{
		def root = new ActiveClosingFrame("UIController")
		def main = root.getActiveFrame()
		
		def oldScalelbl = new Label("Old scale: ")
		def scaleValue = new ActiveLabel(oldScale)
		
		def datatxtbx = new ActiveTextArea(data, null)
		
		def pausebtn = new ActiveButton(null, pause, "Suspend")
		
		def newScalelbl = new Label("Insert new scaler:")
		def newScaletxtbx = new ActiveTextEnterField(null, newScale)
		
		
		def tempContainer = new Container()
		tempContainer.setLayout ( new GridLayout(1,5) )
		tempContainer.add (oldScalelbl)
		tempContainer.add (scaleValue)
		
		tempContainer.add (pausebtn)
		
		tempContainer.add (newScalelbl)
		tempContainer.add (newScaletxtbx.getActiveTextField())
		
		main.setLayout(new BorderLayout())
		main.add(datatxtbx, BorderLayout.CENTER)
		main.add(tempContainer, BorderLayout.SOUTH)
		main.pack()
		main.setVisible(true)
		def network = [root, scaleValue, datatxtbx, pausebtn, newScaletxtbx]
		new PAR (network).run()
	}
}
