# Ontology-Personalized XML IR

A Java web prototype for **personalized information retrieval over XML documents** using a **domain ontology**, **user profiles**, and **semantic matching**.

This repository accompanies the paper:

**Ontology-driven personalized information retrieval for XML documents**  
Ounnaci Iddira, Ahmed-ouamer Rachida, Tai Dinh

---

## Overview

Traditional information retrieval systems often return the same results for the same query, even when users have different interests, backgrounds, or goals. This project explores a personalized retrieval approach for semi-structured XML documents by combining:

- **XML-aware indexing**
- **ontology-based concept representation**
- **user profile modeling**
- **semantic similarity for ranking**

Documents, queries, and user profiles are represented as **weighted concept vectors** derived from the ontology. XML nodes are indexed and ranked to return more relevant, user-adapted results.

---

## Main ideas

The prototype integrates three main components:

1. **Ontology-driven representation**  
   The system uses an OWL ontology to represent domain concepts and semantic relations.

2. **XML document indexing**  
   XML documents are parsed and represented as structured nodes, enabling retrieval at the element or text-node level.

3. **Personalized ranking**  
   User profiles influence the weighting of concepts and the ranking of matching XML fragments.

---

## Project structure

```text
Webprototype/
├── src/
│   ├── java/Prototype/        # Java source code
│   └── conf/
├── web/
│   ├── WEB-INF/               # Web application configuration
│   ├── META-INF/
│   ├── index.jsp              # Main entry page
│   ├── accueil.jsp
│   ├── pageAccueil.jsp
│   ├── PageReq.jsp            # Query interface
│   ├── PageReponse.jsp        # Results page
│   ├── style.css
│   ├── fichier.xml
│   ├── Fichier3.xml
│   └── images/
├── onto.owl                   # Main ontology
├── onto3.owl                  # Additional ontology resource
├── fichier.xml                # XML sample/resource
├── fichier2.xml               # XML sample/resource
└── catalog-v001.xml
```

---

## Core source files

Located in `src/java/Prototype/`:

- `NewServlet.java` — main servlet handling requests and retrieval workflow
- `CalculeScore.java` — relevance score computation
- `IndexationNe.java` — indexing of XML nodes
- `IndexationRequete.java` — query indexing and representation
- `recherche1.java` — retrieval/search support
- `Concept.java`, `ConceptG.java` — concept modeling
- `Elément_Connaissance.java`, `Scénario.java`, `didacticiel.java` — domain-specific knowledge structures
- `calculs.java`, `docpond.java` — helper logic

---

## Technology stack

- **Java**
- **Servlets / JSP**
- **Apache Tomcat**
- **OWL ontology files**
- **Apache Jena**
- **SAX / Xerces XML parsing**
- **MySQL**
- **NetBeans / Ant-style project layout**

---

## Requirements

To run or restore this project, you will usually need:

- Java 8 or later
- Apache Tomcat 7 or compatible servlet container
- MySQL
- Apache Jena libraries
- MySQL JDBC driver
- NetBeans IDE (recommended for this legacy project layout)

---

## Setup notes

This codebase is an academic prototype and will likely need environment-specific fixes before it runs cleanly on another machine.

### 1. Database configuration

The source code includes local JDBC settings such as:

```java
DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","")
```

You should update these values to match your own MySQL setup.

### 2. Local file paths

Some Java files reference ontology files through hard-coded local Windows paths such as:

```text
C:/Users/PCPC/Documents/NetBeansProjects/Webprototype/onto.owl
```

These paths must be replaced with valid local or relative paths in your environment.

### 3. Servlet configuration

The project includes a `web.xml` file under `web/WEB-INF/`. Depending on your environment, you may need to review or correct servlet mappings before deployment.

---

## Running the project

A typical restoration workflow is:

1. Import the project into **NetBeans**
2. Add missing library dependencies
3. Configure **MySQL**
4. Fix hard-coded ontology and XML file paths
5. Build the project with **Ant**
6. Deploy it to **Tomcat**
7. Open the JSP interface in your browser

---

## What the prototype does

At a high level, the system:

1. parses XML documents
2. extracts node descriptors and structure
3. maps content to ontology concepts
4. builds weighted indexes for nodes and queries
5. incorporates user profile information
6. computes semantic similarity scores
7. returns ranked XML fragments as results

---

## Research context

This repository is intended as a **research prototype** accompanying the paper. It reflects an academic implementation rather than a production-ready software package.

It may contain:

- hard-coded configuration values
- environment-specific assumptions
- legacy Java web application structure
- incomplete deployment cleanup

---

## Citation

If you use this code or build upon it in academic work, please cite the associated paper.

```bibtex
@article{iddira2026ontology,
  title={Ontology-driven personalized information retrieval for XML documents},
  author={Iddira, Ounnaci and Ahmed-ouamer, Rachida and Dinh, Tai},
  journal={arXiv preprint arXiv:2603.21139},
  year={2026}
}
```

---
