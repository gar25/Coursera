package week3

object discrete_event {

  trait Simulation {
    type Action = () => Unit
    case class Event(time: Int, action: Action)
    private type Agenda = List[Event]
    private var agenda: Agenda = List()
    private var curtime = 0
    def currentTime: Int = curtime
    def afterDelay(delay: Int)(block: => Unit): Unit = {
      val item = Event(currentTime + delay, () => block)
      agenda = insert(agenda, item)
    }
    private def insert(ag: List[Event], item: Event): List[Event] = ag match {
      case first :: rest if first.time <= item.time =>
        first :: insert(rest, item)
      case _ =>
        item :: ag
    }
    private def loop(): Unit = agenda match {
      case first :: rest =>
        agenda = rest
        curtime = first.time
        first.action()
        loop()
      case Nil =>
    }
    def run(): Unit = {
    	afterDelay(0){
    		println("*** simulation started, time = "+currentTime+" ***")
    	}
    	loop()
    }
    def probe(name: String, wire: Wire): Unit = {
    	def probeAction(): Unit = {
    		println(s"$name $currentTime value = ${wire.getSignal}")
    	}
    	wire addAction probeAction
    }
  }

	trait Parameters {
		def InverterDelay = 2
		def AndGateDelay = 3
		def OrGateDelay = 5
	}

  class Wire {
    private var sigVal = false
    private var actions: List[Action] = List()
    def getSignal: Boolean = sigVal
    def setSignal(s: Boolean): Unit =
      if (s != sigVal) {
        sigVal = s
        actions foreach (_())
      }
    def addAction(a: Action): Unit = {
      actions = a :: actions
      a()
    }
  }

  def inverter(input: Wire, output: Wire): Unit = {
    def invertAction(): Unit = {
      val inputSig = input.getSignal
      afterDelay(InverterDelay) {
        output setSignal !inputSig
      }
      input addAction invertAction
    }
  }

  def andGate(in1: Wire, in2: Wire, output: Wire): Unit = {
    def andAction(): Unit = {
      val in1Sig = in1.getSignal
      val in2Sig = in2.getSignal
      afterDelay(AndGateDelay) {
        output setSignal (in1Sig & in2Sig)
      }
      in1 addAction andAction
      in2 addAction andAction
    }
  }

  def orGate(in1: Wire, in2: Wire, output: Wire): Unit = {
    def orAction(): Unit = {
      val in1Sig = in1.getSignal
      val in2Sig = in2.getSignal
      afterDelay(OrGateDelay) {
        output setSignal (in1Sig | in2Sig)
      }
    }
    in1 addAction orAction
    in2 addAction orAction
  }

}