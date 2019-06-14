import groovy.util.slurpersupport.Node
import groovy.util.slurpersupport.NodeChildren

XmlSlurper xmlSlurper = new XmlSlurper()
def pom = xmlSlurper.parse(new File("pom.xml"))

NodeChildren deps = pom.dependencies

List<String> list = []

deps.childNodes().each {Node node ->
  String groupId = ""
  node.children().each { Node dep ->
    String name = dep.name()
    String value = dep.text()

    if (name == "groupId") {
      groupId = value
    }

    if (name == "artifactId") {
      if (groupId == '${project.groupId}') {
        list.add(value)
      }
    }

  }
}

list.each {
  println(it)
}
