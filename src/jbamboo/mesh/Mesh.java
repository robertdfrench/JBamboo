package jbamboo.mesh;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Pair;
import jbamboo.basetypes.SymmetricPairIterator;


/**
 * This is a passive storage container for MeshNodes and FiniteElements
 * It may be indexed, but it should be agnostic of the Tesselation Policy
 * Or other construction issues.
 * @author robertdfrench
 *
 */
public class Mesh extends JBambooNamespace implements Iterable<MeshNode> {
	protected TreeMap<Integer,MeshNode> nodes;
	protected TreeMapListWrapper listWrapper;
	
	protected Mesh() {
		nodes = new TreeMap<Integer,MeshNode>();
	}
	
	public MeshNode getNode(Integer nodeId) {
		return nodes.get(nodeId);
	}
	
	public void addNode(Integer nodeId, MeshNode node) {
		nodes.put(nodeId,node);
	}

	@Override
	public Iterator<MeshNode> iterator() {
		// TODO Auto-generated method stub
		return nodes.values().iterator();
	}
	
	public Iterator<Pair<MeshNode>> symmetricPairIterator() {
		return new SymmetricPairIterator<MeshNode>(listWrapper);
	}
	
	@Override
	public String toString() {
		return String.format("Mesh with %d nodes", nodes.size());
	}
	
	public class TreeMapListWrapper implements List<MeshNode> {

		@Override
		public boolean add(MeshNode arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void add(int arg0, MeshNode arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean addAll(Collection<? extends MeshNode> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(int arg0, Collection<? extends MeshNode> arg1) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean contains(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public MeshNode get(int arg0) {
			// TODO Auto-generated method stub
			return nodes.get(arg0);
		}

		@Override
		public int indexOf(Object arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator<MeshNode> iterator() {
			// TODO Auto-generated method stub
			return nodes.values().iterator();
		}

		@Override
		public int lastIndexOf(Object arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ListIterator<MeshNode> listIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ListIterator<MeshNode> listIterator(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean remove(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public MeshNode remove(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean removeAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public MeshNode set(int arg0, MeshNode arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return nodes.size();
		}

		@Override
		public List<MeshNode> subList(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T[] toArray(T[] arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
