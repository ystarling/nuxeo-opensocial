package org.nuxeo.opensocial.container.client.view;

import org.nuxeo.opensocial.container.client.presenter.PortletPresenter;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Stéphane Fourrier
 */
public class PortletWidget extends Composite implements
        PortletPresenter.Display {

    private static final String BORDER_COLOR_PREFIX_CSS = "border-color-";

    private static final String HEADER_COLOR_PREFIX_CSS = "header-color-";

    private static final String TITLE_COLOR_PREFIX_CSS = "title-color-";

    private FlowPanel portletPanel;

    private SimplePanel headerPanel;

    private HorizontalPanel headerContent;

    private FlowPanel contentPanel;

    private HTML title;

    public PortletWidget() {
        portletPanel = new FlowPanel();
        portletPanel.addStyleName("widget");
        portletPanel.setWidth("100%");

        headerPanel = new SimplePanel();
        headerPanel.addStyleName("header");
        portletPanel.add(headerPanel);

        headerContent = new HorizontalPanel();
        headerContent.addStyleName("header-content");
        headerPanel.add(headerContent);

        title = new HTML();
        title.setStyleName("title");
        headerContent.add(title);
        headerContent.setCellWidth(title, "100%");

        contentPanel = new FlowPanel();
        contentPanel.addStyleName("content");
        portletPanel.add(contentPanel);

        initWidget(portletPanel);
    }

    public boolean isCollapsed() {
        return contentPanel.isVisible();
    }

    public void showContent() {
        contentPanel.setVisible(true);
    }

    public void hideContent() {
        contentPanel.setVisible(false);
    }

    public void clean() {
        this.removeFromParent();
    }

    public Widget getHeader() {
        return title;
    }

    public void addTool(Widget widget) {
        headerContent.add(widget);
        headerContent.setCellVerticalAlignment(widget,
                HasVerticalAlignment.ALIGN_TOP);
    }

    public void setHeight(long height) {
        if (height != 0) {
            contentPanel.setHeight(height + "px");
        }
    }

    public String getId() {
        return this.getElement().getAttribute("id");
    }

    public String getParentId() {
        return this.getElement().getParentElement().getAttribute("id");
    }

    public void setId(String id) {
        this.getElement().setAttribute("id", id);
    }

    public void addContent(Widget widget) {
        contentPanel.add(widget);
    }

    public void setTitle(String title) {
        this.title.setHTML(title);
    }

    public void setBorderColor(String color) {
        if (color != null) {
            this.getElement().getStyle().setBorderColor(color);
            this.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
        } else {
            this.getElement().getStyle().clearBorderColor();
            this.getElement().getStyle().clearBorderWidth();
        }
    }

    public void setHeaderColor(String color) {
        if (color != null) {
            headerPanel.getElement().getStyle().setProperty(
                    "background",
                    "-moz-linear-gradient(center top , " + color + " 0%, "
                            + color + " 100%) repeat scroll 0 0 transparent");
        } else {
            headerPanel.getElement().getStyle().setProperty(
                    "background",
                    "-moz-linear-gradient(center top , transparent 0%, transparent 100%) repeat scroll 0 0 transparent");
        }
    }

    public void setTitleColor(String color) {
        if (color != null) {
            title.getElement().getStyle().setColor(color);
        } else {
            title.getElement().getStyle().clearColor();
        }
    }

    public void setIcon(String icon) {
        headerContent.addStyleName(icon);
    }

    public Widget asWidget() {
        return this;
    }

    public void startProcessing() {
    }

    public void stopProcessing() {
    }

    public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
        return addDomHandler(handler, MouseDownEvent.getType());
    }

    public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
        return addDomHandler(handler, MouseUpEvent.getType());
    }

    public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
        return addDomHandler(handler, MouseMoveEvent.getType());
    }

    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
        return addDomHandler(handler, MouseOutEvent.getType());
    }
}
