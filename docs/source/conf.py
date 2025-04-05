# -- Project information -----------------------------------------------------

project = 'coding-challenge'
author = 'Annika Weisser'
release = '1.0'

# -- General configuration ---------------------------------------------------

extensions = [
    'sphinx.ext.napoleon',  
    'sphinx.ext.viewcode',  
]

templates_path = ['_templates']
exclude_patterns = []

# -- Options for HTML output -------------------------------------------------

html_theme = 'alabaster' 
html_static_path = ['_static']

# -- Additional options ------------------------------------------------------

# language 
# language = 'de'
