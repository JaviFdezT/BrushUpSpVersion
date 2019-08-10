#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun May  5 01:11:21 2019

@author: javi
"""

file1=open("jehle_verb_database.csv","r")
file2=open("ddbb.sql","w")
n=0
values=[]

for line in file1:
 if "﻿###" not in line:
    data=line.replace("'","`").replace("\n","").split("\",\"")
    data=["'"+i.strip().replace("\"","").lower()+"'" for i in data]
    if len(data)==17:
      verb=["("+data[0],data[1],data[2],data[4],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[15],"1)"]
      if all(len(item)>1 for item in verb):
         values.append(",".join(verb))
         n+=1
         if len(values)%99==0 and n>0:
             query="INSERT INTO VERBS (verb, verb_eng, mood, tense,form_1s, form_2s, form_3s, form_1p, form_2p, form_3p, gerund, pastparticiple, level) VALUES "+",".join(values)+";"
             file2.write(query+"\n")
             values=[]

file1.close()
print(n,"verbs")
file2.close()





