
/*
 * A simple implementation of a web crawler.
 * @author: ArvindRS
 * @date: 05/16/2017
 */

import scala.collection.mutable.Queue
import org.htmlcleaner.HtmlCleaner
import scala.collection.mutable.ListBuffer
import java.io._;
import org.json4s._
import org.json4s.native.JsonMethods._
import scala.util.matching.Regex

case class Config(seed: String,xpath1: String)

object Crawler {
  // Main function
  def main(args: Array[String]) {

    // Load the  crawl config
    val crawlConfig = loadConfig("crawl")

    // Seed URL
    //val seed = "http://www.reuters.com/finance/stocks/overview?symbol=AAPL.OQ"
    val seed = crawlConfig.seed

    // Maintain a set of visited URLs
    val visited = Set()

    // Create a queue to hold the next URLs to be crawled
    val queue = Queue[String](seed)

    while (!queue.isEmpty) {
      // Get the next URL to be crawled
      val url = queue.dequeue()

      // Verify if the URL is valid and is allowed to be crawled
      // stub: verify(url)
      // stub: isAllowed(url)

      // Fetch the URL
      val response = fetch(url)

      // Extract the next set of links
      val links = extractLinks(response, url,crawlConfig)
      links.foreach(println)
      // Add them to the queue
      // stub: updateVisited(url,visited)
      
      links.foreach(queue.enqueue(_))

      // Save the response
      writeToDisk(response, url)
    }
    
    println("quiting...")
  }

  // Function to load the config
  def loadConfig(configType: String): Config = {
    implicit val formats = DefaultFormats

    var fileContents: String = null;
    if (configType == "crawl") {
      fileContents = scala.io.Source.fromFile("/home/arvind/workspace/Crawler/src/resources/crawl_config.json").getLines.mkString
    }
    /*else if(configType == "scrape") {
        fileContents = scala.io.Source.fromFile("/chack/src/test/scala/edu/ucf/cyber/chack/test/crawler/scrape_config.json").getLines.mkString
      }*/
    val json = parse(fileContents)
    val elements = (json \\ "config")
    println(elements)
    val config = elements.extract[Config]
    println(config.seed)

    return config
  }

  // Function to fetch the given URL
  def fetch(url: String): String = {
    val response = scala.io.Source.fromURL(url).mkString
    return response
  }

  // Function to extract the next set of URLs to be crawled
  def extractLinks(response: String, url: String, crawlConfig: Config): List[String] = {
    //println(response)
    val xpath1 = crawlConfig.xpath1
    println(xpath1)
    
    // Using ListBuffer() instead of List() as List() collection is immutable in Scala
    val links = ListBuffer[String]()
    val cleaner = new HtmlCleaner
    val props = cleaner.getProperties
    val rootNode = cleaner.clean(response)
    val elements = rootNode.getElementsByName("a", true)
    
    for (element <- elements) {
      val link = element.getAttributeByName("href")
      if (link != null && link.contains("/www.sdes.ucf.edu/")) {
        links += link
      }
    }
    val domain = getDomain(url)
    println(domain)
    
    var elements1 = rootNode.evaluateXPath(xpath1)
    for(e <- elements1) {
     //println(e)
     links += domain + e.toString().replaceFirst("/", "")
    }
    return links.toList
  }

  // Function to write the response to the disk
  def writeToDisk(response: String, url: String) {
    val fileName = scala.util.Random.nextInt(1000)
    val pw = new PrintWriter(new File(fileName + ".html"))
    pw.write(response)
    pw.close
  }
  
  // Function to get the domain of the URL
  def getDomain(url: String): String = {
    val pattern = "http://[a-z0-9]+\\.[a-z0-9]+\\.[a-z0-9]+/".r
    val domain = (pattern findFirstIn(url))
    println(domain)
    return domain.mkString
  }
}