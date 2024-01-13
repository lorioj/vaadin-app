package com.example.application.tool.views;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.application.helper.TimeEntryHelper;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.PostConstruct;

@PageTitle("TimeSheet")
@Route(value = "timesheet", layout = MainLayout.class)
public class TimeSheetView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8197902421177326339L;
	private static final String DEFAULT_WIDTH = "60px";
	private static final String TASK_WIDTH = "100px";

	private HorizontalLayout layoutFilter = new HorizontalLayout();
	private IntegerField fieldYear = new IntegerField("Year");
	private ComboBox<Integer> fieldMonth = new ComboBox<Integer>("Month");

	private static int monthLength;

	private Button buttonAdd = new Button(VaadinIcon.PLUS.create());

	private Button buttonRemove = new Button(VaadinIcon.MINUS.create());

	private Deque<TimeComponent> lC = new ArrayDeque<>();

	private List<TimeEntryHelper> listHelper = new ArrayList<>();

	private int ps;

	public TimeSheetView() {

		List<Integer> months = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			months.add(i);
		}

		fieldMonth.setItems(months);
		fieldMonth.setWidth("100px");
		fieldMonth.setValue(LocalDate.now().getMonthValue());
		fieldMonth.addValueChangeListener(event -> loadResults());

		fieldYear.setMin(2020);
		fieldYear.setMax(LocalDate.now().getYear());
		fieldYear.setValue(LocalDate.now().getYear());
		fieldYear.addValueChangeListener(event -> loadResults());

		monthLength = LocalDate.of(fieldYear.getValue(), fieldMonth.getValue(), 1).lengthOfMonth();

		for (int i = 0; i < 5; i++) {
			TimeEntryHelper h = new TimeEntryHelper();
			h.setName("john");
			h.setTimeEntries(monthLength);

			listHelper.add(h);
		}

		for (TimeEntryHelper th : listHelper) {
			TimeComponent tc = new TimeComponent(th.getTimeEntries());
			tc.d.setText(th.getName());

			lC.addLast(tc);

		}

		ps = lC.size();
		buttonRemove.addClickListener(e -> {

			if (lC.size() <= ps) {
				return;
			}
			lC.removeLast();
			loadResults();

		});

		buttonAdd.addClickListener(e -> {
			Map<Integer, BigDecimal> newTimeEntries = new HashMap<>();
			for (int i = 1; i <= monthLength; i++) {
				newTimeEntries.put(i, null);
			}
			TimeComponent tc = new TimeComponent(newTimeEntries);
			tc.d.setText("new");
			lC.addLast(tc);
			loadResults();

		});

		loadResults();
	}

	@PostConstruct
	public void init() {

	}

	private void loadResults() {
		removeAll();
		VerticalLayout l = new VerticalLayout();
		l.add(layoutFilter);

		layoutFilter.add(fieldYear, fieldMonth);

		HorizontalLayout layoutActions = new HorizontalLayout();
		layoutActions.add(buttonAdd, buttonRemove);

		l.add(layoutActions);
		l.add(createHeader());

		for (TimeComponent tc : lC) {
			l.add(tc);

		}
		add(l);

	}

	private class TimeComponent extends HorizontalLayout {
		/**
		 * 
		 */
		private static final long serialVersionUID = 5545905530498243526L;
		private Div d = createDiv(DEFAULT_WIDTH);
		private TextField fTast = createTextField(TASK_WIDTH);
		private TextField fDetail = createTextField(TASK_WIDTH);

		private TimeComponent(Map<Integer, BigDecimal> timeEntries) {
			add(d);
			add(fTast);
			add(fDetail);

			for (int k : timeEntries.keySet()) {
				add(createNF(DEFAULT_WIDTH, timeEntries.get(k)));
			}
		}

	}

	private Div createDiv(String width) {
		Div d = new Div();
		d.setWidth(width);
		return d;
	}

	private Div createDiv(String width, String label) {
		Div d = new Div();
		d.setText(label);
		d.setWidth(width);
		return d;
	}

	private HorizontalLayout createHeader() {

		HorizontalLayout l = new HorizontalLayout();
		Div name = createDiv(DEFAULT_WIDTH, "Name");
		Div task = createDiv(TASK_WIDTH, "Task");
		Div detail = createDiv(TASK_WIDTH, "Detail");
		l.add(name, task, detail);

		for (int i = 1; i <= monthLength; i++) {
			l.add(createDiv(DEFAULT_WIDTH, String.valueOf(i)));
		}

		return l;
	}

	private NumberField createNF(String witdh, BigDecimal v) {
		NumberField f = new NumberField();
		f.setWidth(witdh);
		f.setValue(v != null ? v.doubleValue() : null);
		return f;
	}

	private TextField createTextField(String witdh) {
		TextField f = new TextField();
		f.setWidth(witdh);
		return f;
	}

}
