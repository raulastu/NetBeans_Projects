/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vstudionetutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

/**
 *
 * @author rcuser
 */
public class GenASPXpages extends RCGen {

    private String globalNameSpace;
    private String masterPageName;
    private ArrayList<String> labels;
    private String textBoxPrefix = "txt";
    private String labelPrefix = "lab";
    private String dropDownListPrefix = "ddl";
    VersionProperties ve;
    Version version = Version.v2008;

    public GenASPXpages(Connection con, String dbName, String[] tables,
            String tablePrefix, String filePath,
            String globalNameSpace, String masterPageName) {
        super(con, dbName, tables, tablePrefix, filePath, "");
        this.globalNameSpace = globalNameSpace;
        this.masterPageName = masterPageName;
        labels = new ArrayList<String>();

    }

    /* PROJECT NAME IS projectNamePrefix + WL*/
    public String genPage() throws SQLException, IOException {
        ve = new VersionProperties(version);
        String res = "";
        for (String table : tables) {
            PrintWriter pw = new PrintWriter(
                    new FileWriter(new File(
                    filePath + Util.cap(Util.Sql2CSPatt(table)) + "Form.aspx")));
            ArrayList<String[]> csFields = new ArrayList<String[]>();
            ArrayList<String[]> sqlFields = new ArrayList<String[]>();
            ArrayList<String[]> controls = new ArrayList<String[]>();
            LinkedHashMap<String, String[]> s = new LinkedHashMap<String, String[]>();

            HashMap<String, String[]> fks = Util.getForeignKeys(tablePrefix + table);
            String webCode = "";
            webCode += "<%@ Page Language=\"C#\" " +
                    "EnableEventValidation=\"false\"" +
                    "AutoEventWireup=\"true\" " + ve.CodeBehind + "=\"" + Util.cap(Util.Sql2CSPatt(table)) + "Form.aspx.cs\" " +
                    "Inherits=\"" + globalNameSpace + "WL." + Util.cap(Util.Sql2CSPatt(table)) + "Form\" Title=\"" + Util.sqlToLabelCap(table) + "\"";
            if (!masterPageName.equals("")) {
                webCode += "MasterPageFile=\"~/" + masterPageName + "\" ";
            }
            webCode += " %>\n" +
                    "<%@ Register \n" +
                    "Assembly=\"AjaxControlToolkit\" \n" +
                    "Namespace=\"AjaxControlToolkit\"\n" +
                    "TagPrefix=\"ajaxToolkit\"\n";
            webCode += " %>\n";
            if (masterPageName.equals("")) {
                webCode += "<html>\n" +
                        "<head>\n";
            } else {
                webCode += "<asp:Content ID=\"Content1\" ContentPlaceHolderID=\"head\" runat=\"server\">";
            }
            webCode += "\n" +
                    "    <style type=\"text/css\">\n" +
                    "        .style2\n" +
                    "        {\n" +
                    "            width: 200px;\n" +
                    "            height: 0px;\n" +
                    "            text-align: left;\n" +
                    "        }\n" +
                    "        .style3\n" +
                    "        {\n" +
                    "            width: 112px;\n" +
                    "            height: 0px;\n" +
                    "            text-align: left;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "\n";
            if (masterPageName.equals("")) {
                webCode += "</head>\n" +
                        "<body>\n";
                webCode += "<form id=\"" + Util.cap(Util.Sql2CSPatt(table)) + "FormTag\" runat=\"server\">\n";
            } else {
                webCode += "</asp:Content>\n";
                webCode += "<asp:Content ID=\"Content2\" ContentPlaceHolderID=\"ContentPlaceHolder1\" runat=\"server\">\n";
            }
            webCode += "    <asp:ScriptManager ID=\"ScriptManager" + Util.cap(Util.Sql2CSPatt(table)) + "\" runat=\"server\">\n" +
                    "        </asp:ScriptManager>\n";
            webCode += "    <div style=\"text-align: center\">\n" +
                    "        <table >\n";
            ResultSet rs = conn.createStatement().executeQuery("select * from " + tablePrefix + table + " where 2 = 1");
            int count = rs.getMetaData().getColumnCount();
            int cascadingDDLCounter = 1;
            for (int i = 1; i <= count; i++) {
                String sqlField = rs.getMetaData().getColumnLabel(i);
                String type = rs.getMetaData().getColumnTypeName(i);

                String csVar = Util.Sql2CSPatt(sqlField);
                String csType = new Util().mapsTypes.get(type).csType;
                String aspType = fks.containsKey(sqlField) ? dropDownListPrefix : textBoxPrefix;
                String[] csVarCSTypeASPVarASPType;
                if (fks.containsKey(sqlField)) {
                    String FatherTable = fks.get(sqlField)[0];
                    String t = Util.cap(FatherTable.replaceAll("\\b" + tablePrefix, ""));
                    String aspVar = dropDownListPrefix + Util.cap(Util.Sql2CSPatt(t));
                    csVarCSTypeASPVarASPType = new String[]{csVar, csType, aspVar, aspType};
                } else {
                    String aspVar = textBoxPrefix + Util.cap(csVar);
                    csVarCSTypeASPVarASPType = new String[]{csVar, csType, aspVar, aspType};
                }

                csFields.add(csVarCSTypeASPVarASPType);
                sqlFields.add(new String[]{sqlField, type});
                System.out.println(csFields.get(csFields.size() - 1)[0]);

                if (isIdentifier(Util.Sql2CSPatt(sqlField), table)) {
                    continue;
                }

                ArrayList<String[]> tree = new ArrayList<String[]>();
                retrieveFields(tree, sqlField, fks);
                assert tree.size() >= 1;
                Collections.reverse(tree);
                for (String[] strings : tree) {
                    String controlType = strings[0];
                    String controlName = strings[1];
                    String controlLab = strings[2];
                    String parent = "";
                    webCode +=
                            "            <tr>\n" +
                            "                <td class=\"style3\">\n" +
                            "                    <asp:Label ID=\"" + labelPrefix + Util.cap(controlName) + "\" runat = \"server\" Text = \"" + controlLab + ":\" > </asp:Label>\n" +
                            "                </td>\n";
                    if (controlType.equals(textBoxPrefix)) {
                        assert tree.size() == 1;
                        controls.add(new String[]{textBoxPrefix, controlName, csType});
                        webCode +=
                                "                <td class=\"style2\">\n" +
                                "                        <asp:TextBox ID=\"" + textBoxPrefix + Util.cap(controlName) + "\" runat=\"server\" Columns=\"20\"></asp:TextBox>\n" +
                                "                </td>\n";
                    } else if (controlType.equals(dropDownListPrefix)) {
                        controls.add(new String[]{dropDownListPrefix, controlName, csType});
                        webCode +=
                                "                <td class=\"style2\">\n" +
                                "                        <asp:DropDownList ID=\"" + dropDownListPrefix + Util.cap(controlName) + "\" runat=\"server\" ></asp:DropDownList>\n" +
                                "                        <ajaxToolkit:CascadingDropDown \n" +
                                "                            ID=\"CascadingDropDown" + cascadingDDLCounter++ + "\"\n" +
                                "                            runat=\"server\"\n";
                        if (!parent.equals("")) {
                            webCode += "                            ParentControlID=\"" + parent + "\"\n";
                        }
                        webCode +=
                                "                            TargetControlID =\"" + dropDownListPrefix + Util.cap(controlName) + "\"\n" +
                                "                            Category=\"" + Util.cap(controlName) + "\"\n" +
                                "                            PromptText=\"Seleccione " + Util.cap(controlName) + "\"\n" +
                                "                            ServicePath=\"" + Util.cap(Util.Sql2CSPatt(table)) + "WS.asmx\"\n";
                        if (!parent.equals("")) {
                            webCode += "                            ServiceMethod = \"getList" + Util.cap(controlName) + "For" + parent + "\"\n";
                        } else {
                            webCode += "                            ServiceMethod = \"getList" + Util.cap(controlName) + "\"\n";
                        }
                        webCode += "                        />\n" +
                                "                </td>\n";
                        parent = dropDownListPrefix + Util.cap(controlName);
                    }
                    webCode += "            </tr>\n";
                }
            }
            webCode += "            <tr>\n" +
                    "                <td class=\"style3\">\n" +
                    "                    &nbsp;</td>\n" +
                    "                <td class=\"style2\">\n" +
                    "                    &nbsp;</td>\n" +
                    "            </tr>\n" +
                    "        </table> \n" +
                    "    </div>\n";
            webCode +=
                    "        <div style=\"text-align: center; height:54px\">\n" +
                    "                    <asp:Button ID=\"butAgregar\" runat=\"server\" Text=\"Agregar Nuevo\" \n" +
                    "                        onclick=\"butAgregar_Click\" />\n" +
                    "&nbsp;&nbsp;&nbsp;\n" +
                    "                    <asp:Button ID=\"butCancelar\" runat=\"server\" Text=\"Cancelar\" Width=\"122px\" \n" +
                    "                        onclick=\"butCancelar_Click\" />\n" +
                    "                    <br />\n" +
                    "                    <asp:Label ID=\"labAviso\" runat=\"server\" BackColor=\"#CCFFFF\"></asp:Label>\n" +
                    "    </div>\n";
            //GridView
            webCode +=
                    "        <div style=\"text-align: center; height: 160px;\" >\n" +
                    "        <asp:GridView ID=\"gv" + Util.cap(Util.Sql2CSPatt(table)) + "\" runat=\"server\" AutoGenerateColumns=\"False\" \n" +
                    "                onrowcancelingedit=\"gv" + Util.cap(Util.Sql2CSPatt(table)) + "_RowCancelingEdit\"\n" +
                    "                onrowdeleting=\"gv" + Util.cap(Util.Sql2CSPatt(table)) + "_RowDeleting\" onrowediting=\"gv" + Util.cap(Util.Sql2CSPatt(table)) + "_RowEditing\" \n" +
                    "                onrowupdating=\"gv" + Util.cap(Util.Sql2CSPatt(table)) + "_RowUpdating\" >\n" +
                    "            <Columns>\n" +
                    "                <asp:CommandField ShowEditButton=\"True\" />\n" +
                    "                <asp:CommandField ShowDeleteButton=\"True\" />\n";

            for (int i = 0; i < sqlFields.size(); i++) {
                String strings[] = csFields.get(i);
                String atrib = "";
                if (isIdentifier(strings[0], table)) {
                    atrib = "ReadOnly=\"True\"";
                }
                webCode +=
                        "                <asp:BoundField " +
                        "DataField=\"" + Util.Sql2CSPatt(strings[0]) + "\" " +
                        "HeaderText=\"" + Util.sqlToLabelCap(sqlFields.get(i)[0]) + "\" " + atrib + "/>\n";
            }
            webCode += "            </Columns>\n" +
                    "        </asp:GridView>\n" +
                    "        </div>\n" +
                    "\n";
            if (!masterPageName.equals("")) {
                webCode += "</asp:Content>";
            } else {
                webCode += "</form>\n" +
                        "</body>\n" +
                        "</html>\n";
            }
            pw.write(webCode);
            createCodeBehind(table, csFields, controls);
            res += webCode;
            pw.close();
        }
        if (masterPageName.equals("")) {
            createIndexPage(tables);
        }
        return res;
    }

    public String createIndexPage(String[] tables) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(filePath + "Index.aspx"));
        String indexCode = "<%@ Page Language=\"C#\" AutoEventWireup=\"true\" " + ve.CodeBehind + "=\"Index.aspx.cs\" " +
                "Inherits=\"" + globalNameSpace + "WL.Index\" %>\n" +
                "<%@ Register \n" +
                "Assembly=\"AjaxControlToolkit\" \n" +
                "Namespace=\"AjaxControlToolkit\"\n" +
                "TagPrefix=\"ajaxToolkit\" %>\n" +
                "\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n";
        indexCode += "<html xmlns=\"http://www.w3.org/1999/xhtml\" >\n" +
                "<head runat=\"server\">\n" +
                "    <title>" + Util.sqlToLabelCap(globalNameSpace) + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form id=\"form1\" runat=\"server\">\n" +
                "    \n" +
                "    <div>\n" +
                "    <asp:ScriptManager ID=\"ScriptManager1\" runat=\"server\">\n" +
                "    </asp:ScriptManager>\n" +
                "    \n" +
                "    <ajaxToolkit:TabContainer ID=\"tc1\" runat=\"server\"         \n" +
                "        ActiveTabIndex=\"0\">\n";
        for (String table : tables) {
            indexCode += "      <ajaxToolkit:TabPanel ID=\"TabPanel" + Util.cap(Util.Sql2CSPatt(table)) + "\" runat=\"server\" \n" +
                    "          HeaderText=\"" + Util.sqlToLabelCap(table) + "\" >\n" +
                    "            <ContentTemplate>\n" +
                    "                <iframe width=\"100%\" height=\"100%\" \n" +
                    "                runat=\"server\" id=\"iframe" + Util.cap(Util.Sql2CSPatt(table)) + "\" " +
                    "src=\"" + Util.cap(Util.Sql2CSPatt(table)) + "Form.aspx\">\n" +
                    "                </iframe>\n" +
                    "            </ContentTemplate>\n" +
                    "           </ajaxToolkit:TabPanel >    \n";
        }
        indexCode += "  </ajaxToolkit:TabContainer>\n" +
                "    </div>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n";
        pw.write(indexCode);
        pw.close();
        return indexCode;
    }

    private void retrieveFields(ArrayList<String[]> fieldsToAdd, String field, HashMap<String, String[]> fks) throws SQLException {
        if (fks.containsKey(field)) {
            String fatherTable = fks.get(field)[0];
            // type, controlName, Caption
            String tableName = fatherTable.replaceFirst("\\b" + tablePrefix, "");
            fieldsToAdd.add(new String[]{dropDownListPrefix,
                        Util.Sql2CSPatt(tableName),
                        Util.sqlToLabelCap(fatherTable.replaceFirst("\\b" + tablePrefix, ""))});
            LinkedHashMap<String, String[]> fkOfFather = Util.getForeignKeys(fatherTable);

            if (fkOfFather.size() == 1) {
                String fkOfFatherTable = "";
                for (String string : fkOfFather.keySet()) {
                    fkOfFatherTable = string;
                }
                retrieveFields(fieldsToAdd, fkOfFatherTable, fkOfFather);
            }
        } else if (fieldsToAdd.size() == 0) {
            fieldsToAdd.add(new String[]{textBoxPrefix,
                        Util.Sql2CSPatt(field),
                        Util.sqlToLabelCap(field)});
        }
    }

    private String createCodeBehind(String table, ArrayList<String[]> CSVarTypeASPVarType,
            ArrayList<String[]> controls) throws IOException, SQLException {
        PrintWriter pw = new PrintWriter(new FileWriter(
                new File(filePath + Util.cap(Util.Sql2CSPatt(table)) + "Form.aspx.cs")));
        //usings
        String classCode = "using System;\n" +
                "using System.Collections;\n" +
                "using System.Configuration;\n" +
                "using System.Data;\n" +
                "using System.Web;\n" +
                "using System.Web.Security;\n" +
                "using System.Web.UI;\n" +
                "using System.Web.UI.WebControls;\n" +
                "using System.Web.UI.WebControls.WebParts;\n" +
                "using System.Web.UI.HtmlControls;\n" +
                "using " + globalNameSpace + "DAL;\n" +
                "\n";
        //class
        classCode +=
                "namespace " + globalNameSpace + "WL {\n" +
                "    public partial class " + Util.cap(Util.Sql2CSPatt(table)) + "Form : System.Web.UI.Page {\n" +
                "        protected void Page_Load(object sender, EventArgs e) {\n" +
                "            if (!IsPostBack) {\n" +
                "                refreshGv();\n" +
                "                setControlVisibility(false);\n" +
                "            }\n" +
                "        }\n";

        classCode +=
                "        private void refreshGv() {\n" +
                "            gv" + Util.cap(Util.Sql2CSPatt(table)) + ".DataSource = " + Util.cap(Util.Sql2CSPatt(table)) + "DA.getList" + Util.cap(Util.Sql2CSPatt(table)) + "();\n" +
                "            gv" + Util.cap(Util.Sql2CSPatt(table)) + ".DataBind();\n" +
                "        }\n" +
                "\n";

        classCode +=
                "        protected void butAgregar_Click(object sender, EventArgs e) {\n" +
                "            if (butAgregar.Text.Equals(\"Agregar Nuevo\")) {\n" +
                "                clearTxts();\n" +
                "                setControlVisibility(true);\n" +
                "                butAgregar.Text = \"Guardar\";\n" +
                "                labAviso.Text = \"\";\n" +
                "            }else {\n";
        for (String[] aCSVarTypeASPVarType : CSVarTypeASPVarType) {
            if (isIdentifier(aCSVarTypeASPVarType[0], table)) {
                continue;
            }
            classCode +=
                    "                " + aCSVarTypeASPVarType[1] + " " + aCSVarTypeASPVarType[0] + " = ";
            boolean parse = false;
            if (!aCSVarTypeASPVarType[1].toLowerCase().equals("string")) {//String or string
                parse = !parse;
                classCode += aCSVarTypeASPVarType[1] + ".Parse(";
            }
            if (aCSVarTypeASPVarType[3].equals(textBoxPrefix)) { //is TextBox
                classCode += "" + aCSVarTypeASPVarType[2] + ".Text" + (parse ? ")" : "") + ";\n";
            } else if (aCSVarTypeASPVarType[3].equals(dropDownListPrefix)) {
                classCode += "" + aCSVarTypeASPVarType[2] + ".SelectedValue.ToString()" + (parse ? ")" : "") + ";\n";
            }
        }
        classCode +=
                "                " + Util.cap(Util.Sql2CSPatt(table)) + "DA.insert(";


        ArrayList<Node> fields = new Util().getFields(tablePrefix + table, conn);
        System.err.println(table + " " + fields);

        String vars = "";
        for (Node node : fields) {
            if (node.insertable) {
                vars += node.csName + ", ";
            }
        }
        vars = vars.substring(0, vars.length() - 2);

        classCode += vars + ");\n" +
                "                labAviso.Text = \"Ud. ha agregado un nuevo registro en " + Util.cap(table) + "\";\n" +
                "                clearTxts();\n" +
                "                refreshGv();\n" +
                "            } \n" +
                "        }\n" +
                "\n";
        //END INSERTAR METHOD
        classCode +=
                "        protected void butCancelar_Click(object sender, EventArgs e) {\n" +
                "            clearTxts();\n" +
                "            setControlVisibility(false);\n" +
                "            butAgregar.Text = \"Agregar Nuevo\";\n" +
                "            labAviso.Text = \"\";\n" +
                "        }\n" +
                "\n";
        classCode +=
                "        protected void gv" + Util.cap(Util.Sql2CSPatt(table)) + "_RowEditing(object sender, GridViewEditEventArgs e) {\n" +
                "            gv" + Util.cap(Util.Sql2CSPatt(table)) + ".EditIndex = e.NewEditIndex;\n" +
                "            refreshGv();\n" +
                "        }\n" +
                "\n";
        /**@TODO*/
        classCode +=
                "        protected void gv" + Util.cap(Util.Sql2CSPatt(table)) + "_RowUpdating(object sender, GridViewUpdateEventArgs e) {\n";
        int rowCount = 2;
        for (String string[] : CSVarTypeASPVarType) {
            classCode += "            " + string[1] + " " + string[0] + " = ";
            boolean parse = false;
            if (!string[1].toLowerCase().equals("string")) {//String or string
                parse = !parse;
                classCode += string[1] + ".Parse(";
            }
            if (string[0].equals("id" + Util.cap(Util.Sql2CSPatt(table)))) {
                classCode += "gv" + Util.cap(Util.Sql2CSPatt(table)) + ".Rows[e.RowIndex].Cells[" + rowCount++ + "].Text" + (parse ? ")" : "") + ";\n";
            } else {
                classCode += "((TextBox)gv" + Util.cap(Util.Sql2CSPatt(table)) + ".Rows[e.RowIndex].Cells[" + rowCount++ + "].Controls[0]).Text" + (parse ? ")" : "") + ";\n";
            }
        }
        classCode += "            " + Util.cap(Util.Sql2CSPatt(table)) + "DA.update(";
        String vars2 = "";
        for (Node node : fields) {
            vars2 += node.csName + ", ";
        }
//        for (String[] strings : CSVarTypeASPVarType) {
//            vars2 += strings[0] + ", ";
//        }
        vars2 = vars2.substring(0, vars2.length() - 2);
        classCode += vars2 + ");\n";
        classCode +=
                "            gv" + Util.cap(Util.Sql2CSPatt(table)) + ".EditIndex = -1;;\n" +
                "            refreshGv();\n" +
                "        }\n" +
                "\n";
        //END UPDATING METHOD
        classCode +=
                "        protected void gv" + Util.cap(Util.Sql2CSPatt(table)) + "_RowCancelingEdit(object sender, GridViewCancelEditEventArgs e) {\n" +
                "            gv" + Util.cap(Util.Sql2CSPatt(table)) + ".EditIndex = -1;\n" +
                "            refreshGv();\n" +
                "        }\n" +
                "\n";
        classCode +=
                "        protected void gv" + Util.cap(Util.Sql2CSPatt(table)) + "_RowDeleting(object sender, GridViewDeleteEventArgs e) {\n";

        boolean parse = false;
        for (Node node : fields) {
            if (node.isPK) {
                classCode += "            " + node.csType + " " + node.csName + " = ";
                if (!node.csType.equals("string")) {
                    parse = true;
                    classCode += node.csType + ".Parse(";
                }
                classCode += "gv" + Util.cap(Util.Sql2CSPatt(table)) + ".Rows[e.RowIndex].Cells[2].Text" + (parse ? ")" : "") + ";\n";
            }
        }
//        classCode += "gv" + Util.cap(Util.Sql2CSPatt(table)) + ".Rows[e.RowIndex].Cells[2].Text" + (parse ? ")" : "") + ";\n";
        classCode += "            " + Util.cap(Util.Sql2CSPatt(table)) + "DA.delete(";
        String params = "";
        for (Node node : fields) {
            if (node.isPK) {
                params += node.csName + ", ";
            }
        }
        if (params.length() > 2) {
            params = params.substring(0, params.length() - 2);
        }
        classCode += params + ");\n" +
                "            refreshGv();\n" +
                "        }\n" +
                "\n";

        //clearTxts Method
        classCode += "        private void clearTxts() {\n";
        for (String[] strings : CSVarTypeASPVarType) {
            if (isIdentifier(strings[0], table) || !strings[3].equals(textBoxPrefix)) {
                continue;
            }
            classCode += "            " + strings[2] + ".Text=\"\";\n";
        }
        classCode += "        }\n" +
                "\n";
        //end clearTxts Method
        classCode +=
                "        private void setControlVisibility(bool visible) {\n" +
                "            butCancelar.Visible = visible;\n";
        for (String[] control : controls) {
            if (isIdentifier(control[0], table)) {
                continue;
            }
            classCode += "            " + "lab" + Util.cap(control[1]) + ".Visible = visible;\n";
            classCode += "            " + control[0] + Util.cap(control[1]) + ".Visible = visible;\n";
        }
        classCode +=
                "        }\n" +
                "    }\n" +
                "}\n";
        pw.write(classCode);
        pw.close();
        return classCode;
    }

    private boolean isIdentifier(String csVar, String table) {
        if (csVar.equals("id" + Util.cap(Util.Sql2CSPatt(table)))) //  || csVar.equals("cod" + VSNetUtil.cap(VSNetUtil.Sql2CSPatt(table)))) {
        {
            return true;
        }
        return false;
    }

    /**
     * Capitalize a word
     * @param st
     * @return
     */
    public static void goAlmacenTienda() throws Exception {
        Connection con = MasterConn.getConnection();
        String[] tables = {
            "tienda",
            "almacen",
            "producto",
            "inventario",
            "transferencia",
            "gasto_mensual"
        };
        GenASPXpages ob = new GenASPXpages(con, "AlmacenTienda",
                tables, "t_", "E:/Visual Studio Projects/Almacen Tienda/WL",
                "AlmacenTienda", "");
        ob.genPage();
    }

    public static void runMatricula() throws Exception {
        Connection con = MasterConn.getConnection();
        String[] tables = {
            //batista
            "universidad",
            "facultad",
            "escuela",
            "plan_curricular",
            //paco
            "ciclo",
            "curso",
            "tipo_docente",
            "docente",
            "carga_lectiva",
            "tipo_carga_lectiva",
            "prerequisitos",
            //rc
            "departamento",
            "provincia",
            "distrito",
            "alumno",
            "usuario",
            "sancionado",
            "tipo_sancion",
            "matricula",
            "matricula_detalle",
            //wilfer
            "tipo_pago",
            "pago",
            "semestre_academico",
            "usuario_docente",
            "administrativo",
            "tipo_administrativo"
        };
        GenASPXpages ob = new GenASPXpages(con, "Matricula",
                tables, "t_", "E:/Matricula/Classes/WL/",
                "Matricula", "");
        ob.genPage();
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(VSNetUtil.sqlToLabelCap("numero_id_com"));
//        goAlmacenTienda();
        runMatricula();
    }
}
