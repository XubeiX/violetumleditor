package com.horstmann.violet.product.diagram.abstracts.property;

import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import com.horstmann.violet.framework.util.SerializableEnumeration;
import com.horstmann.violet.product.diagram.abstracts.AbstractGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.ClassNodeMorph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.InterfaceNodeMorph;

public class Morph extends SerializableEnumeration {

	private static AbstractGraph graph;

	public static void setGraph(AbstractGraph grapht) {
		graph = grapht;
	}

	private static final long serialVersionUID = -7185987624987560065L;

	public void morph(INode node) {

		if (node instanceof ClassNodeMorph) {
			if (this == TOINTERFACE) {
				InterfaceNodeMorph inter = new InterfaceNodeMorph();
				
				//pobranie wszystkich dostêpnych edgy
				ArrayList<IEdge> lista = new ArrayList<IEdge>(graph.getAllEdges());
				for (IEdge iEdge : lista) {
					System.out.println(iEdge);
				}
				//ustawienie nazwy na interfejst
				MultiLineString name = ((ClassNodeMorph) node).getName();
				name.setText("<<interface>>\n" + ((ClassNodeMorph) node).getName());
				inter.setName(name);
				//skopiowanie metod
				inter.setMethods(((ClassNodeMorph) node).getMethods());
				inter.setMorph(TOINTERFACE);
				//skopiowanie lokalizacji usuniêcie starego i narysowanie nowego w tym miejscu
				inter.setLocation(((ClassNodeMorph) node).getLocation());
				graph.removeNode(node);
				graph.addNode(inter, inter.getLocation());
				//sprawdzenie jakie edge zosta³y
				System.out.println("after remove");
				ArrayList<IEdge> lista2 = new ArrayList<IEdge>(graph.getAllEdges());
				for (IEdge iEdge : lista2) {
					System.out.println(iEdge);

				}
				//wyznaczenie brakuj¹cych edgy
				//i dorysowanie ich na nowo
				//TODO nie dzia³a poprawnie ta czeœæ
				System.out.println("Pozosta³e niewidoczne");
				lista.removeAll(lista2);
				for (IEdge iEdge : lista) {
					System.out.println(iEdge);
					if(iEdge.getStart()==node){
					iEdge.setStart(inter);
					iEdge.setStartLocation(inter.getConnectionPoint(iEdge));
					}
					if(iEdge.getEnd()==node){
						iEdge.setEnd(inter);
						iEdge.setEndlocation(inter.getConnectionPoint(iEdge));
					}
					inter.addConnection(iEdge);
					graph.connect(iEdge, iEdge.getStart(), iEdge.getStartLocation(), iEdge.getEnd(),
							iEdge.getEndLocation(), iEdge.getTransitionPoints());
				}

			}

		}
		if (node instanceof InterfaceNodeMorph)

		{
			if (this == TOCLASS)
				System.out.println("Z INTERFEJSU NA KLASE");

		}

	}

	public static final Morph TOCLASS = new Morph();
	public static final Morph TOINTERFACE = new Morph();

}
