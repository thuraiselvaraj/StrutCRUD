public interface Codes{
    byte ADMIN= 0x01;
    byte STAFF= 0x02;
    byte STUDENT= 0x03;
    byte NO_LOGIN = 0x04;
    byte CRED_WRONG= 0x05;
    byte ERROR=0x06;
    byte SUCCESS=0x07;
    byte EMAIL_EXISTS=0x08;
    byte STAFF_ID_EXISTS=0x09;
    byte STUDENT_ID_EXISTS=0x0A;
    byte NO_SUCCESS=0x0B;
    byte TAMPERED=0x0C;
    
    String[] Strcodes=new String[]{"ADMIN","STAFF","STUDENT","NO_LOGIN","CRED_WRONG","ERROR",
                                  "SUCCESS","EMAIL_EXISTS","STAFF_ID_EXISTS","STUDENT_ID_EXISTS",
                                   "NO_SUCCESS","TAMPERED"};

    public static String stringify(byte b){
        for(int i=1;i<=Strcodes.length;i++){
            if(b==i){
                return Strcodes[i];
            }
        }
        return "ERROR";
    }
}