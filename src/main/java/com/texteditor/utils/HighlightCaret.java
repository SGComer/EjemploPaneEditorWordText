package com.texteditor.utils;

import java.awt.Color;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class HighlightCaret extends DefaultCaret {

    private static final long serialVersionUID = 1L;
    
    private Highlighter.HighlightPainter unfocusedPainter = new DefaultHighlighter.DefaultHighlightPainter(new Color(0,120,215));
    private Highlighter.HighlightPainter focusedPainter = new DefaultHighlighter.DefaultHighlightPainter(new Color(0,120,215));
    
    private boolean isFocused;
    private Color selectionColor;

    public HighlightCaret() {             
        setBlinkRate(500);         
    }
    
    public HighlightCaret(Color selectionColor) {             
        setBlinkRate(500);         
        this.selectionColor = selectionColor;
        if(selectionColor != null) {
            unfocusedPainter = new DefaultHighlighter.DefaultHighlightPainter(selectionColor);
            focusedPainter = new DefaultHighlighter.DefaultHighlightPainter(selectionColor);
        }
        
    }
    
    @Override
    protected Highlighter.HighlightPainter getSelectionPainter() {
        setBlinkRate(500); // otherwise is disabled, stopped
        return isFocused ? focusedPainter/*super.getSelectionPainter()*/ : unfocusedPainter;
    }

    @Override
    public void setSelectionVisible(boolean hasFocus) {
        if (hasFocus != isFocused) {
            isFocused = hasFocus;
            super.setSelectionVisible(false);
            super.setSelectionVisible(true);
        }
    }
}