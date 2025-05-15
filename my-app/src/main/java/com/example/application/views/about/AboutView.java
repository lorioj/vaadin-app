package com.example.application.views.about;

import java.util.ArrayList;
import java.util.List;

import com.example.application.model.Product;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import jakarta.annotation.PostConstruct;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {
//	
	private TextField f = new TextField("f1");
	private TextField f2 = new TextField("f2");
//	private Binder<Product> binder = new Binder<Product>(Product.class);
	private Binder<Product> binder = new Binder(Product.class);
	
	
	private Grid<Product> grid = new Grid<>();
//	private Product p;
//	
//	private List<Product> l;
//	
	private VerticalLayout layout = new VerticalLayout();
//	
	private FlexLayout fLayout = new FlexLayout();
	
	private HorizontalLayout hl = new HorizontalLayout();
	
	private FormLayout fl = new FormLayout(f);

    public AboutView() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("This place intentionally left empty");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        
        
//        add(layout);
//        add(grid);
//        
//        
//    	grid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES);
//		grid.addColumn(o ->  "🟢").setTextAlign(ColumnTextAlign.CENTER).setFlexGrow(0).setHeader("Active").setWidth("60px");
//        grid.addColumn(o -> o.getName()).setHeader("Name").setKey("i");
        
//        grid.getColumnByKey("i");  
//        ListDataProvider<Product> dataProvider =
//                DataProvider.ofCollection(l);
          
//        layout.setAlignItems(Alignment.STRETCH);
//        Button b = new Button("Button");
//        layout.add(f, b); 
        
//        binder.bind(f, Product::getName, Product::setName);
//        binder.bind(f, "title");
//        binder.forField(f).withValidator(new org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator());
        
//        
//        BeanValidationBinder<Product> bb = new BeanValidationBinder<Product>(Product.class);
//        
//        bb.bind(f, "name");
//        
//        bb.bind(f, Product::getName, Product::setName);
//        
//        DataProvider.fromFilteringCallbacks(null, null);
//        
//        DataProvider<String, String> d = new AbstractDataProvider<String, String>();
//        
//        
        
//        fLayout.setWidth("300px");
//        
//        
//        f.setWidth("200px");
//        f2.setWidth("500px");
//        
//        fLayout.add(f, f2);
//       
        
//        hL.setAlignItems(getDefaultHorizontalComponentAlignment());
//        
//        add(f, f2);
//        f.getStyle().set("margin-left", "auto");
//        expand(fLayout);
//        fLayout.setJustifyContentMode(JustifyContentMode.END);
//        
        hl.add(f, f2);
        f.setHeight("100%");
        hl.setHeight("500px");
        hl.setAlignItems(Alignment.STRETCH);
        
        add(hl);
        
//        UI.getCurrent().nv
//        HasUrlParameter<T>
        
//        VaadinSession.getCurrent().close()
        
//        VaadinSession.getCurrent().getSession().invalida
//        grid.setRowsDraggable(isAttached())
        
//        DragStartEvent<Component>
//        
//        DropEve
        
    }
    
    
    
    @PostConstruct
    private void init() {
    	List<Product> p = new ArrayList<>();
    	p.add(new Product((long)1, "choco", 2.20));
    	grid.setItems(p);
    }

}
