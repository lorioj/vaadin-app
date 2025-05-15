package com.example.application.master.views;

import java.util.ArrayList;
import java.util.List;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.PostConstruct;

@PageTitle("Sale")
@Route(value = "sale", layout = MainLayout.class)
public class SaleEncodeView extends VerticalLayout implements AfterNavigationObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7027470153965470678L;
	private TextField fieldInput = new TextField();
	
	
	private List<SaleComponent> listComponent = new ArrayList<>();
	
	
	public SaleEncodeView() {
		System.err.println("constructor");
		
		for(int i = 0; i < 5; i++) {
			SaleComponent sc = new SaleComponent();
			sc.fieldProduct.setValue("Product");
			sc.unitPrice.setValue(0.3);
			sc.createPaymentModeField();
			listComponent.add(sc);
			
		}
		
		for(SaleComponent sc : listComponent) {
			add(sc);
		}
		
	
	}
	
	@PostConstruct
	public void init() {
		System.err.println("init");
	
		
	}
	
	private class SaleComponent extends HorizontalLayout{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2514539126522548224L;
		private TextField fieldProduct = new TextField("Product");
		private NumberField unitPrice = new NumberField("Unit Price");

		public SaleComponent() {
			add(fieldProduct);
			add(unitPrice);
		}

		private void createPaymentModeField() {
			for(int i = 0; i < 2; i++) {
				NumberField f = new NumberField();
				f.setLabel("Cash");
				f.setValue(30.3);
				add(f);
			}
		}
		
		
	}
	

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		
	}

}
