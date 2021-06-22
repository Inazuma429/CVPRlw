import re
import requests
from bs4 import BeautifulSoup
import pymysql

num = 0;
def insertdata(data,n):
    conn=pymysql.connect("localhost","root","Inazuma","paqu",charset='utf8')
    cur=conn.cursor()
    sql="INSERT INTO lunwen(Timu,Zhaiyao,Lianjie) VALUES(%s,%s,%s)"
    try:
        cur.execute(sql,data)
        conn.commit()
        print(n)
    except:
        conn.rollback()
        print("ERROR")
    conn.close()

#网址，这个网站提供了CVPR论文的题目，链接信息，还有pdf格式的论文全文
r = requests.get('http://openaccess.thecvf.com/ICCV2019.py')
soup = BeautifulSoup(r.text,'lxml')
#简单的按条件寻找标签
for item in soup.find_all('a',href=re.compile('content_ICCV_2019/html/')):
    num=num+1
    timu = item.string
    lianjie = item['href']
    #这里拿出的'href'没有http那段开头，想再读取链接进入网址，我在前面拼了上去，这个网址里可以获取摘要信息
    ra = requests.get('http://openaccess.thecvf.com/'+item['href'])
    soupa = BeautifulSoup(ra.text, 'lxml')
    diva = soupa.find(attrs={"id": "abstract"})
    #根据给出的BUG信息，网址有的论文点进去发生了Not Found错误，需要加个判断语句
    if(diva == None):
        zhaiyao=-1
    else:
        zhaiyao=diva.string
    insertdata((timu,zhaiyao,lianjie),num)