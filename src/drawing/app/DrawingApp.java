package drawing.app;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class DrawingApp extends JFrame {
private JButton ccButton,aButton;
private JCheckBox fine, coarse;
private JRadioButton Line, Rectangle, Oval, Freehand;
private JSlider fhSlider;

    public DrawingApp() {
        setLayout(new BorderLayout());
        setSize(1000,800);
        
        //Canvas
        JPanel canvas = new JPanel();
        canvas.setBorder(new TitledBorder(new EtchedBorder(), "Canvas"));
        canvas.setPreferredSize(new Dimension(1000,800));
        
        //Controll Panel
        JPanel cpanel = new JPanel();
        cpanel.setBorder(new TitledBorder(new EtchedBorder(), "Controll Panel"));
        cpanel.setPreferredSize(new Dimension(200, 100));
        
        
                //Panels within the CPANEL////
                
                            
                            //DRAWING POSITION//
        JPanel dp = new JPanel();
        dp.setBorder(new TitledBorder(new EtchedBorder(), "Drawing Position"));
        dp.setPreferredSize(new Dimension(160,50));
        cpanel.add(dp);
        
                            //DRAWING TOOLS//

        JPanel dt = new JPanel();
        dt.setBorder(new TitledBorder(new EtchedBorder(), "Drawing Tools"));
        dt.setPreferredSize(new Dimension(160,200));
        cpanel.add(dt);
        
        Line = new JRadioButton("Line");
        Rectangle = new JRadioButton("Rectangle");
        Oval = new JRadioButton("Oval");
        Freehand = new JRadioButton("Freehand");
        dt.setLayout(new BoxLayout(dt,BoxLayout.PAGE_AXIS)); // vertical oriantion
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
        fs.setPreferredSize(new Dimension(160,100));
        cpanel.add(fs);
        fhSlider = new JSlider(0,20);
        fhSlider.setMajorTickSpacing(5);
        fhSlider.setMinorTickSpacing(1);
        fhSlider.setPaintTicks(true);
        fhSlider.setPaintLabels(true);
        fhSlider.setPreferredSize(new Dimension(130,70));
        fs.add(fhSlider);
        
        
                            //GRID//

        JPanel grid = new JPanel();
        grid.setBorder(new TitledBorder(new EtchedBorder(), "Grid"));
        grid.setPreferredSize(new Dimension(160,80));
        cpanel.add(grid);
        
        fine = new JCheckBox("Fine");
        coarse = new JCheckBox("Coarse");
        grid.setLayout(new BoxLayout(grid,BoxLayout.PAGE_AXIS));
        grid.add(fine);
        grid.add(coarse);
        
        
                            //COLOUR//

        JPanel colour = new JPanel();
        colour.setBorder(new TitledBorder(new EtchedBorder(), "Colour"));
        colour.setPreferredSize(new Dimension(160,100));
        cpanel.add(colour);
        
        
        
        
        //Message Area
        JPanel messagearea = new JPanel();
        messagearea.setBorder(new TitledBorder(new EtchedBorder(), "Massage Area"));
        messagearea.setPreferredSize(new Dimension(200, 100));
        
        //Buttons
        ccButton = new JButton("Clear Canvas");
        aButton = new JButton("Animate");
        cpanel.add(ccButton);
        cpanel.add(aButton);
        ccButton.setPreferredSize(new Dimension(160,50));
        aButton.setPreferredSize(new Dimension(160,50));
       
        
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
        
        
        
        
        add(canvas,BorderLayout.CENTER);
        add(cpanel,BorderLayout.LINE_START);
        add(messagearea,BorderLayout.PAGE_END);
        add(menuBar,BorderLayout.PAGE_START);
        
        //Button 
        
        
        
        
        
        pack();
        setVisible(true);
        
     
    }

    public static void main(String[] args) {
        DrawingApp FrameTest = new DrawingApp();

    }

}
