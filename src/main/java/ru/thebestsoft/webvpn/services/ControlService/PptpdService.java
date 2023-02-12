package ru.thebestsoft.webvpn.services.ControlService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PptpdService {

    private Answer exCommand(String command) {
        String[] exCommand = new String[] {"/bin/bash", "-c", command};
        Answer answer = new Answer();
        try {
            Process process = new ProcessBuilder(exCommand).start();
            BufferedReader readerError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            BufferedReader readerAnswer = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = "";
            while((line = readerError.readLine()) != null) {
                answer.setError(answer.getError() + line + "<br />");
            }

            while((line = readerAnswer.readLine()) != null) {
                answer.setAnswer(answer.getAnswer() + line+ "<br />");
            }
        } catch (Exception e) {
            answer.setError("Ошибка: " + e);
        }
        return answer;
    }
    public Answer getInfo(){
        return exCommand("service pptpd status"); //service pptpd status
    }
    public void doStart(){
        exCommand("service pptpd start");
    }
    public void doStop(){
        exCommand("service pptpd stop");
    }
    public void doRestart(){
        exCommand("service pptpd restart");
    }
}
