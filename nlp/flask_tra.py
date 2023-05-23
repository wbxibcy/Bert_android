from flask import Flask
from flask import request
import json
from tra import my_bert

app = Flask(__name__)

@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"

@app.route("/sentence", methods=['POST'])
def my_sentence():
    if request.method == 'POST':
        argsJson = request.data.decode('utf-8')
        argsJson = json.loads(argsJson)
        # print(argsJson)
        result = my_bert(sentence=argsJson)
        result = json.dumps(result, ensure_ascii=False)
        # print(type(result))
        return result
    else:
        return " 'it's not a POST operation! "
    
@app.route("/level", methods=['GET'])
def my_level():
    if request.method == 'GET':
        
        return "hello world!!!!!!"
    else:
        return " 'it's not a GET operation! "
    
if __name__ == '__main__':
	app.run(port=5000)
