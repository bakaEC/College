VERSION 5.00
Begin {C62A69F0-16DC-11CE-9E98-00AA00574A4F} VB小东西 
   Caption         =   "UserForm1"
   ClientHeight    =   5430
   ClientLeft      =   120
   ClientTop       =   465
   ClientWidth     =   9210
   OleObjectBlob   =   "VB小东西.frx":0000
   StartUpPosition =   1  '所有者中心
End
Attribute VB_Name = "VB小东西"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False

Private Sub CommandButton1_Click()
    TextBox1.Text = "单击"
    
End Sub


Private Sub CommandButton1_DblClick(ByVal Cancel As MSForms.ReturnBoolean)
    TextBox1.Text = "双击"
End Sub

