//package view;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyChangeListener;
//
//import javax.swing.*;
//
//import interface_adapter.get_all_surveys.GetMySurveysController;
//import interface_adapter.get_all_surveys.GetMySurveysViewModel;
//import interface_adapter.signup.SignupViewModel;
//
//public class GetMySurveysView extends JPanel implements ActionListener, PropertyChangeListener {
//
//    private final String viewName = "get all surveys";
//    private final GetMySurveysViewModel getmysurveysViewModel;
//    private final GetMySurveysController getmysurveysController;
//
//    private final JButton back;
//
//    public GetMySurveysView(GetMySurveysController controller, GetMySurveysViewModel getmysurveysViewModel) {
//
//        this.getmysurveysController = controller;
//        this.getmysurveysViewModel = getmysurveysViewModel;
//        getmysurveysViewModel.addPropertyChangeListener(this);
//
//        final JLabel title = new JLabel(GetMySurveysViewModel.TITLE_LABEL);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        JScrollPane scrollPane = new JScrollPane(panel);
//
//        final JPanel buttons = new JPanel();
//        back = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
//        buttons.add(back);
//
//        back.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        getmysurveysController.switchToLoginView();
//                    }
//                }
//        );
//
//        this.add(title);
//        this.add(scrollpane);
//        this.add(buttons);
//
//
//
//    }
//
//    public String getViewName() {
//        return viewName;
//    }
//
//}
