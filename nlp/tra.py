from transformers import AutoTokenizer, AutoModelForSequenceClassification
import torch

tokenizer = AutoTokenizer.from_pretrained('nlptown/bert-base-multilingual-uncased-sentiment')
model = AutoModelForSequenceClassification.from_pretrained('nlptown/bert-base-multilingual-uncased-sentiment')

def my_bert(sentence):
    tokens = tokenizer.encode(sentence, return_tensors='pt')
    result = model(tokens)
    level = int(torch.argmax(result.logits))+1
    print(int(torch.argmax(result.logits))+1)
    return level

my_bert("it is a bad day.")