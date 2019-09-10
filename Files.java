package SEProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Files
 */
@WebServlet("/Files")
public class Files extends HttpServlet {

	ArrayList<String> word_list;
	ArrayList<String> common_words;
	static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		BufferedReader reader;

		/*
		 * read a file which contains words and definitions, split lines into word and
		 * its definition and store them in two lists word list and definition list
		 */
		try {
			reader = new BufferedReader(new FileReader("C:/Users/definitions (2).txt"));
			String line = reader.readLine();
			ArrayList<String> definition_list = new ArrayList<String>();
			word_list = new ArrayList<String>();

			while (line != null) {
				String[] numbers = line.split(":"); // splitting line in the file into word and definition
				word_list.add(numbers[0]);
				definition_list.add(numbers[1]);
				line = reader.readLine();
			}
			common_words = new ArrayList<String>();

			/*
			 * check if any glossary words are contained in the definition and store them in
			 * a list
			 */
			for (int k = 0; k < word_list.size(); k++) {
				common_words = new ArrayList<String>();
				String[] test = definition_list.get(k).split(" ");
				for (int i = 0; i < test.length; i++) {
					for (int j = 0; j < word_list.size(); j++) {

						if (word_list.get(j).equalsIgnoreCase(test[i]) == true) {
							common_words.add(test[i]);

						}

					}
				}
				if (common_words.isEmpty() == true) {
					common_words.add("No related term in glossary");
				}
				response.getWriter().println("<h4><a id=\"" + definition_list.get(k) + "\"" + ""
						+ " onclick=\"wordMeaning(id)\">" + word_list.get(k) + "</a></h4>");

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
