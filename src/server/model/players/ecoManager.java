import java.io.*;

		/**
                  *@author Genc
                  * 
                  */
public class ecoManager
{
        public static void main(String args[])
        {
                  
                  /**
                  * Don't put notes, though. It will cause problems.
                  */

		
                int[] rares = {1050, 1051, 1057, 1053, 1054, 1055, 1056, 1057, 1058, 1042, 1048, 1038, 1046, 1044, 1040};
                File charFolder;
                BufferedWriter bw;
                BufferedReader br;
                BufferedWriter tmpwr;
                BufferedReader tmpr;
                boolean overwrite = false;
                String read;
                String dir = "Data/characters/";
                double done = 0;
                int percent = 0;
                int percentRounded = 0;

                charFolder = new File(dir);

                if(!charFolder.exists())
                {
                        System.out.println("The directory "+dir+" was not found.");
                        return;
                }
                else
                if(charFolder.list().length == 0)
                {
                        System.out.println("The specified directory is empty.");
                        return;
                }
                String file[] = charFolder.list();
                for(String s : file)
                {
                        try
                        {
                                boolean isAdmin = false;
                                File charFile = new File(dir+s);
                                File tmpFile = new File(dir+s+".tmp");
                                tmpFile.createNewFile();
                                br = new BufferedReader(new FileReader(charFile));
                                tmpwr = new BufferedWriter(new FileWriter(tmpFile, true));

                                while((read = br.readLine()) != null)
                                {
                                        if(read.contains("character-rights"))
                                        {
                                                if(read.charAt(read.length() - 1) > '1')
                                                {
                                                        System.out.println("Bank saved for admin "+s.substring(0, s.indexOf(".")));
                                                        isAdmin = true;
                                                }
                                        }

                                        if(read.equals("[ITEMS]") || read.equals("[EQUIPMENT]"))
                                        {
                                                if(!isAdmin)
                                                {
                                                        overwrite = true;
                                                }
                                        }

                                        if(read.equals("[FRIENDS]") || read.equals("[LOOK]"))
                                                overwrite = false;

                                        if(!overwrite)
                                        {
                                                tmpwr.write(read);
                                                tmpwr.newLine();
                                        }
                                        else if(!isAdmin)
                                        {
                                                boolean found = false;
                                                for(int i : rares)
                                                {
                                                        if(read.contains("\t" + i + "\t") || read.contains("\t" + (i + 1) + "\t"))
                                                        {
                                                                found = true;
                                                        }
                                                }
                                                if(!found)
                                                {
                                                        tmpwr.write(read);
                                                        tmpwr.newLine();
                                                }
                                        }
                                }
                                tmpwr.flush();
                                tmpwr.close();
                                br.close();

                                charFile.delete();

                                bw = new BufferedWriter(new FileWriter(charFile, true));
                                tmpr = new BufferedReader(new FileReader(tmpFile));

                                while((read = tmpr.readLine()) != null)
                                {
                                        bw.write(read);
                                        bw.newLine();
                                }

                                bw.flush();
                                bw.close();
                                tmpr.close();
                                tmpFile.delete();

                                done++;

                                if((percent = (int)(done / file.length * 100)) % 5 < 5 && percent - percent % 5 != percentRounded)
                                        System.out.println((percentRounded = (int)(percent - percent % 5))+"%");
                        }
                        catch(IOException Ioe)
                        {
                                Ioe.printStackTrace();
                        }
                }
                System.out.println("Finished!");
        }
}