package drawing.app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;




public class DrawingApp extends JFrame {

    private JButton ccButton, aButton, colourButton;
    private JCheckBox fine, coarse;
    private JRadioButton Line, Rectangle, Oval, Freehand;
    private JSlider fhSlider;
    private JTextArea messageArea;
    private JLabel mousePosition;
    private Canvas canvas;
    private String drawTool="";
    
    
    //freehand variables
    private final int MAX_FREEHAND_PIXELS = 10000;
    private Color[] freehandColour = new Color[MAX_FREEHAND_PIXELS];
    private int[][] fxy = new int[MAX_FREEHAND_PIXELS][3];
    private int freehandPixelsCount = 0;
    private int thickness=10;
    private Color selectedColour = new Color(0.0F, 0.0F, 0.0F);
    
    
    //line variables
    private final int MAX_LINE_COUNT = 10;
    private Color[] lineColour = new Color[MAX_LINE_COUNT];
    private int[][] lxy = new int[MAX_LINE_COUNT][3];
    private int lineCount = 0;
    
    //rectangle variables
    
    //ellipse variables
    
    public void freehand(MouseEvent event,int thickness, Color selectedColour){
        
        freehandColour[freehandPixelsCount] = selectedColour ;
        fxy[freehandPixelsCount][0] = event.getX(); // x-coordinate
        fxy[freehandPixelsCount][1] = event.getY(); // y-coordinate
        fxy[freehandPixelsCount][2] = thickness; // dimension
        freehandPixelsCount++;
        messageArea();
        
    }
    
    
    class CanvasMouseListener implements MouseListener{
        
        
        public void mousePressed(MouseEvent event) {
 
        }
        
        
        public void mouseReleased(MouseEvent event) {
 
        }

         public void mouseClicked(MouseEvent event) {

        }
         

        public void mouseEntered(MouseEvent event) {
 
        }
        

        public void mouseExited(MouseEvent event) {
 
        }
    }
    
    
    
    
    class Canvas extends JPanel
    {
        // Called every time there is a change in the canvas contents.
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);        
            draw(g);
        }
    }
    
    class ClearActionListener implements ActionListener{
        
        public void actionPerformed(ActionEvent event){
            
            fxy = null;
            freehandPixelsCount = 0;
            fxy = new int[MAX_FREEHAND_PIXELS][3];
            canvas.repaint();
            messageArea();
            
        }
        
        
    }
    
    
    
    class FreehandSliderListener implements ChangeListener{
        
        public void stateChanged(ChangeEvent event){
            
            thickness = fhSlider.getValue();
        }
    }
    
    class buttonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent event){
            JColorChooser colourChooser = new JColorChooser(selectedColour);
            Color newColour = colourChooser.showDialog(null, "Choose new drawing colour", selectedColour);
            selectedColour = newColour;
            colourButton.setBackground(newColour);
        }
        
    } 
    
    class radioButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent event){
            messageArea();
            if (Freehand.isSelected()){
                
                drawTool="Freehand";
                
            }
            
        }
        
        
        
    }
    
    
    class CanvasMouseMotionListener implements MouseMotionListener {

        public void mouseMoved(MouseEvent event) {
            
            mousePosition.setText(String.format("%1dpx, %1dpx", event.getX(), event.getY()));
            
            
        }

       
        public void mouseDragged(MouseEvent event) {
            switch (drawTool){
                case "Freehand":
                freehand(event,thickness,selectedColour);
                break;
            }
            
            
            
            mouseMoved(event);
            canvas.repaint();

            
        }
    }
    
    public void messageArea(){
    messageArea.setText(null);
    switch (drawTool){
        case "Freehand":
            if(MAX_FREEHAND_PIXELS>freehandPixelsCount){
                messageArea.setText(null);
                messageArea.append("Remaining ink: "+(MAX_FREEHAND_PIXELS - freehandPixelsCount));
            }
            break;
        }
                
        
    }
        
    
     
    
    
    class CheckBoxListener implements ChangeListener{
        
        public void stateChanged(ChangeEvent e){
            canvas.repaint();
        }
        
    }
    
    
    
    void draw(Graphics g){
        
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        

        
        //GRID DRAWING
        if(fine.isSelected()) {
            g.setColor(new Color(0.8F,0.8F,0.8F));
            
            for (int i=0; i<w; i+=10){
                g.drawLine(i,0,i,h);
            }
            
            for (int j=0; j<h; j+=10){
               g.drawLine(0,j, w, j);
            }
            
        } 
        
        if(coarse.isSelected()) {
            g.setColor(new Color(0.6F,0.6F,0.6F));
            for (int i=0; i<w; i+=50){
                g.drawLine(i,0,i,h);
            }
            for (int j=0; j<h; j+=50){
               g.drawLine(0,j, w, j);
                    
            }
            
        }
        
        
        //FREEHAND DRAWING
        
        
        for(int i=0; i<MAX_FREEHAND_PIXELS; i++){
            g.setColor(freehandColour[i]);
            g.fillRect(fxy[i][0],fxy[i][1],fxy[i][2],fxy[i][2]);
            
        }
    
            
    
    }

    
    public DrawingApp() {

        setLayout(new BorderLayout());
        setSize(1000, 800);
        
        

        
        
        //Canvas
        canvas = new Canvas();
        canvas.setBorder(new TitledBorder(new EtchedBorder(), "Canvas"));
        canvas.setPreferredSize(new Dimension(1000, 800));
        canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        canvas.addMouseMotionListener(new CanvasMouseMotionListener());
        
        

        //Controll Panel
        JPanel cpanel = new JPanel();
        cpanel.setBorder(new TitledBorder(new EtchedBorder(), "Control Panel"));
        cpanel.setPreferredSize(new Dimension(200, 100));

        //Panels within the CPANEL////
        
        
        
        //DRAWING POSITION//
        JPanel dp = new JPanel();
        dp.setBorder(new TitledBorder(new EtchedBorder(), "Drawing Position"));
        dp.setPreferredSize(new Dimension(160, 50));
        mousePosition = new JLabel();
        dp.add(mousePosition);
        //mousePosition.setText(event,getX);
        cpanel.add(dp);

        //DRAWING TOOLS//
        JPanel dt = new JPanel();
        dt.setBorder(new TitledBorder(new EtchedBorder(), "Drawing Tools"));
        dt.setPreferredSize(new Dimension(160, 200));
        cpanel.add(dt);

        Line = new JRadioButton("Line");
        Rectangle = new JRadioButton("Rectangle");
        Oval = new JRadioButton("Oval");
        Freehand = new JRadioButton("Freehand");
        Freehand.addActionListener(new radioButtonListener());
        dt.setLayout(new BoxLayout(dt, BoxLayout.PAGE_AXIS)); // vertical oriantion
        dt.add(Line);
        dt.add(Rectangle);
        dt.add(Oval);
        dt.add(Freehand);
        ButtonGroup group = new ButtonGroup();
        group.add(Line);
        group.add(Rectangle);
        group.add(Oval);
        group.add(Freehand);

        //FREEHAND SIZE//
        JPanel fs = new JPanel();
        fs.setBorder(new TitledBorder(new EtchedBorder(), "Freehand Size"));
        fs.setPreferredSize(new Dimension(160, 100));
        cpanel.add(fs);
        fhSlider = new JSlider(0, 20);
        fhSlider.setMajorTickSpacing(5);
        fhSlider.setMinorTickSpacing(1);
        fhSlider.setPaintTicks(true);
        fhSlider.setPaintLabels(true);
        fhSlider.setPreferredSize(new Dimension(130, 70));
        fhSlider.addChangeListener(new FreehandSliderListener());
        fs.add(fhSlider);

        //GRID//
        JPanel grid = new JPanel();
        grid.setBorder(new TitledBorder(new EtchedBorder(), "Grid"));
        grid.setPreferredSize(new Dimension(160, 80));
        cpanel.add(grid);

        fine = new JCheckBox("Fine");
        coarse = new JCheckBox("Coarse");
        grid.setLayout(new BoxLayout(grid, BoxLayout.PAGE_AXIS));
        fine.addChangeListener(new CheckBoxListener());
        coarse.addChangeListener(new CheckBoxListener());
        grid.add(fine);
        grid.add(coarse);

        //COLOUR button//
        JPanel colour = new JPanel();
        colour.setBorder(new TitledBorder(new EtchedBorder(), "Colour"));
        colour.setPreferredSize(new Dimension(160, 100));
        colourButton = new JButton();
        colourButton.setPreferredSize(new Dimension(50, 50));
        colourButton.addActionListener(new buttonListener());
        colour.add(colourButton);
        cpanel.add(colour);

        //Message Area
        messageArea = new JTextArea();
        messageArea.setBackground(canvas.getBackground());
        messageArea.setBorder(new TitledBorder(new EtchedBorder(), "Massage Area"));
        messageArea.setPreferredSize(new Dimension(200, 100));

        //Buttons
        ccButton = new JButton("Clear Canvas");
        aButton = new JButton("Animate");
        cpanel.add(ccButton);
        cpanel.add(aButton);
        ccButton.setPreferredSize(new Dimension(160, 50));
        aButton.setPreferredSize(new Dimension(160, 50));
        ccButton.addActionListener(new ClearActionListener());

        //Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu fileHelp = new JMenu("Help");
        JMenuItem fileSaveMenuItem = new JMenuItem("Save");
        JMenuItem fileLoadMenuItem = new JMenuItem("Load");
        JMenuItem fileExitMenuItem = new JMenuItem("Exit");
        JMenuItem fileAboutMenuItem = new JMenuItem("About");
        fileMenu.add(fileSaveMenuItem);
        fileMenu.add(fileLoadMenuItem);
        fileMenu.add(fileExitMenuItem);
        fileHelp.add(fileAboutMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(fileHelp);

        add(canvas, BorderLayout.CENTER);
        add(cpanel, BorderLayout.LINE_START);
        add(messageArea, BorderLayout.PAGE_END);
        add(menuBar, BorderLayout.PAGE_START);

        
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        messageArea();
        pack();
        setVisible(true);

    }

    public static void main(String[] args) {
        DrawingApp FrameTest = new DrawingApp();

    }

}
