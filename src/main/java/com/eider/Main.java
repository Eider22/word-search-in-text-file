package com.eider;

import javax.swing.JOptionPane;

import com.eider.controllers.contracts.ISystemController;
import com.eider.controllers.dtos.DetailsWordTimesQuantityInFileDto;
import com.eider.controllers.dtos.DetailsWordTimesQuantityInFolderDto;
import com.eider.controllers.dtos.ResponseDto;
import com.eider.controllers.implementations.SystemController;

public class Main {

    private static ISystemController systemController = new SystemController(null);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        try {
            Integer option = -1;
            String inputFolder;
            String inputWord;

            do {
                try {
                    option = Integer.parseInt(JOptionPane.showInputDialog("Ingrese una opción " +
                            "\n1. Buscar catidad de veces que esta una palabra en una carpeta " +
                            "\n0. Terminar programa"));

                    switch (option) {
                        case 1:
                            inputFolder = JOptionPane.showInputDialog("Ingrese la ruta de la carpeta");
                            inputWord = JOptionPane.showInputDialog("Ingrese la palabra que desea buscar");

                            ResponseDto<DetailsWordTimesQuantityInFolderDto> response = systemController
                                    .getDetailsWordTimesQuantityInPathByExtension(
                                            inputFolder, inputWord,
                                            ".txt");

                            String output = "";

                            if (!response.getSucces()) {
                                output = response.getMessage();
                                JOptionPane.showMessageDialog(null, output);
                                break;
                            }

                            for (DetailsWordTimesQuantityInFileDto detail : response.getData()
                                    .getDetailsWordTimesQuantityInFiles()) {
                                output += detail.getFileName() + " " + detail.getQuantity() + " veces\n";
                            }

                            output += "\nTotal: " + response.getData().getQuantity() + " veces";
                            JOptionPane.showMessageDialog(null, output);
                            break;
                        default:
                            break;
                    }

                } catch (NumberFormatException e) {
                    option = null;
                    JOptionPane.showMessageDialog(null, "Opción inválida. Inténtelo de nuevo");
                }

            } while (option == null || option != 0);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}