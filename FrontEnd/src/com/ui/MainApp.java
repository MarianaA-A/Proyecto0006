package com.ui;

import com.backend.dao.DocenteDAO;
import com.backend.dao.DocenteDAOImpl;
import com.backend.exception.DAOException;
import com.backend.model.Docente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainApp extends JFrame {
    private final DocenteDAO dao = new DocenteDAOImpl();
    private final DefaultTableModel model = new DefaultTableModel();
    private final JTable table = new JTable(model);

    public MainApp() {
        super("Gestión de Docentes (2007-2013)");
        initUI();
        refreshList();
    }

    private void initUI() {
        model.setColumnIdentifiers(new String[]{"ID", "IES", "Género", "Tipo Doc", "Nivel", "Dedicación", "Contrato", "Depto", "Municipio", "Conteo 2013"});
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(table);

        JButton btnAdd = new JButton("Crear");
        btnAdd.addActionListener(this::onCreate);
        JButton btnEdit = new JButton("Editar");
        btnEdit.addActionListener(this::onEdit);
        JButton btnDelete = new JButton("Eliminar");
        btnDelete.addActionListener(this::onDelete);
        JButton btnRefresh = new JButton("Refrescar");
        btnRefresh.addActionListener(e -> refreshList());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(btnAdd);
        buttons.add(btnEdit);
        buttons.add(btnDelete);
        buttons.add(btnRefresh);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttons, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);

        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void refreshList() {
        try {
            List<Docente> lista = dao.getAll();
            model.setRowCount(0);
            for (Docente d : lista) {
                model.addRow(new Object[]{d.getId(), d.getNombreIES(), d.getGeneroDocente(), d.getTipoDocumento(), 
                                          d.getNivelFormacion(), d.getTiempoDedicacion(), d.getTipoContrato(), 
                                          d.getDepartamento(), d.getMunicipio(), d.getConteo2013()});
            }
        } catch (DAOException e) {
            showError(e.getMessage(), e);
        }
    }

    private void onCreate(ActionEvent e) {
        try {
            String codigoIES = JOptionPane.showInputDialog(this, "Código Institución:");
            if (codigoIES == null) return;
            String nombreIES = JOptionPane.showInputDialog(this, "Nombre IES:");
            if (nombreIES == null) return;
            String genero = JOptionPane.showInputDialog(this, "Género (FEMENINO/MASCULINO):");
            if (genero == null) return;
            String tipoDoc = JOptionPane.showInputDialog(this, "Tipo Documento (NACIONAL/EXTRANJERO):");
            if (tipoDoc == null) return;
            String nivel = JOptionPane.showInputDialog(this, "Nivel Formación:");
            if (nivel == null) return;
            String dedicacion = JOptionPane.showInputDialog(this, "Tiempo Dedicación:");
            if (dedicacion == null) return;
            String contrato = JOptionPane.showInputDialog(this, "Tipo Contrato:");
            if (contrato == null) return;
            String depto = JOptionPane.showInputDialog(this, "Departamento:");
            if (depto == null) return;
            String municipio = JOptionPane.showInputDialog(this, "Municipio:");
            if (municipio == null) return;
            String conteo = JOptionPane.showInputDialog(this, "Conteo 2013:");
            if (conteo == null) return;

            Docente d = new Docente(null, codigoIES, nombreIES, genero, tipoDoc, nivel, dedicacion, 
                                    contrato, depto, municipio, Integer.parseInt(conteo));
            dao.create(d);
            refreshList();
        } catch (DAOException ex) {
            showError("Error creando docente", ex);
        } catch (NumberFormatException ex) {
            showError("Error: Conteo debe ser un número", ex);
        }
    }

    private void onEdit(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un docente para editar.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String id = (String) model.getValueAt(row, 0);
        try {
            Docente d = dao.getById(id);
            if (d == null) {
                JOptionPane.showMessageDialog(this, "Docente no encontrado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String codigoIES = JOptionPane.showInputDialog(this, "Código Institución:", d.getCodigoInstitucion());
            if (codigoIES == null) return;
            String nombreIES = JOptionPane.showInputDialog(this, "Nombre IES:", d.getNombreIES());
            if (nombreIES == null) return;
            String genero = JOptionPane.showInputDialog(this, "Género:", d.getGeneroDocente());
            if (genero == null) return;
            String tipoDoc = JOptionPane.showInputDialog(this, "Tipo Documento:", d.getTipoDocumento());
            if (tipoDoc == null) return;
            String nivel = JOptionPane.showInputDialog(this, "Nivel Formación:", d.getNivelFormacion());
            if (nivel == null) return;
            String dedicacion = JOptionPane.showInputDialog(this, "Tiempo Dedicación:", d.getTiempoDedicacion());
            if (dedicacion == null) return;
            String contrato = JOptionPane.showInputDialog(this, "Tipo Contrato:", d.getTipoContrato());
            if (contrato == null) return;
            String depto = JOptionPane.showInputDialog(this, "Departamento:", d.getDepartamento());
            if (depto == null) return;
            String municipio = JOptionPane.showInputDialog(this, "Municipio:", d.getMunicipio());
            if (municipio == null) return;
            String conteo = JOptionPane.showInputDialog(this, "Conteo 2013:", d.getConteo2013().toString());
            if (conteo == null) return;

            d.setCodigoInstitucion(codigoIES);
            d.setNombreIES(nombreIES);
            d.setGeneroDocente(genero);
            d.setTipoDocumento(tipoDoc);
            d.setNivelFormacion(nivel);
            d.setTiempoDedicacion(dedicacion);
            d.setTipoContrato(contrato);
            d.setDepartamento(depto);
            d.setMunicipio(municipio);
            d.setConteo2013(Integer.parseInt(conteo));
            dao.update(d);
            refreshList();
        } catch (DAOException ex) {
            showError("Error editando docente", ex);
        } catch (NumberFormatException ex) {
            showError("Error: Conteo debe ser un número", ex);
        }
    }

    private void onDelete(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un docente para eliminar.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String id = (String) model.getValueAt(row, 0);
        int resp = JOptionPane.showConfirmDialog(this, "¿Eliminar docente seleccionado?","Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp != JOptionPane.YES_OPTION) return;
        try {
            dao.delete(id);
            refreshList();
        } catch (DAOException ex) {
            showError("Error eliminando docente", ex);
        }
    }

    private void showError(String message, Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, message + "\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.setVisible(true);
        });
    }
}