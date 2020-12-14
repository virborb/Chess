package model;

import controller.ChessController;
import view.ImagePanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class MouseAction implements MouseListener, MouseMotionListener {

    private ImagePanel comp;
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;

    public MouseAction(ImagePanel component) {
        this.comp = component;
    }

    public ImagePanel getComp() {
        return comp;
    }

    public int getMyX() {
        return myX;
    }

    public int getMyY() {
        return myY;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();

        myX = comp.getX();
        myY = comp.getY();
    }

    @Override
    public abstract void mouseReleased(MouseEvent e);

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = e.getXOnScreen() - screenX;
        int deltaY = e.getYOnScreen() - screenY;
        comp.setLocation(myX + deltaX, myY + deltaY);
    }

    @Override
    public void mouseMoved(MouseEvent e) { }
}
