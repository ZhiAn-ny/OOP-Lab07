package it.unibo.oop.lab.nesting2;

import java.util.List;

public class OneListAcceptable<T> implements Acceptable<T> {
	private int current;
	private List<T> accepted;
	
	public OneListAcceptable(List<T> list){
		this.accepted = list;
		this.current = 0;
	}

	class AcceptorImpl implements Acceptor<T>{
		private OneListAcceptable<T> list;
		
		public AcceptorImpl(OneListAcceptable<T> list){
			this.list = list;
		}

		@Override
		public void accept(T newElement) throws ElementNotAcceptedException {
			if (this.list.current < this.list.accepted.size() 
					&& newElement == this.list.accepted.get(current)) {
				this.list.current++;
			} else {
				throw new ElementNotAcceptedException(newElement);
			}
		}

		@Override
		public void end() throws EndNotAcceptedException {
			if (this.list.current < this.list.accepted.size()) {
				throw new EndNotAcceptedException();
			}
		}
	}
	
	@Override
	public Acceptor<T> acceptor() {
		return this.new AcceptorImpl(this);
	}
	
	

}
