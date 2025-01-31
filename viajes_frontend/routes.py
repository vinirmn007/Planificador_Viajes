from flask import Blueprint, json, render_template, request, redirect, url_for, flash, session
import requests

routes = Blueprint('routes', __name__)

URL = "http://localhost:8000/myapp/"

@routes.route('/')
def home():
    r = requests.get(URL + "ciudades")
    data = r.json().get('data')
    return render_template('index.html', cities=data)

@routes.route('/findRoute/<algoritmo>/<origen>/<destino>')
def findRoute(algoritmo, origen, destino):
    url = URL + f'grafo/minPath/{algoritmo}/{origen}/{destino}'

    r = requests.get(url)
    data = r.json().get('data')

    return render_template('findRoute.html')

@routes.route('/city/save', methods=['POST'])
def saveCity():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"nombre": form["name"],
                "pais": form["country"],
                "region": form["region"],
                "clima": form["climate"],
                "desc": form["description"],
                "longitud": float(form["lat"]),
                "latitud": float(form["long"])}
    r = requests.post(URL + 'ciudades/save', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha guardado correctamente', category='info')
        return redirect('/')
    else:
        flash('No se ha podido guardar', category='error')
    return redirect('/') 

@routes.route('/road/save', methods=['POST'])
def saveRoad():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"origen": int(form["origin"]),
                "destino": int(form["destination"]),
                "nombre": form["name"],
                "distancia": float(form["distance"]),
                "tipo": form["road_type"],
                "tiempo": float(form["time"])}
    r = requests.post(URL + 'carreteras/save', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha guardado correctamente', category='info')
        return redirect('/')
    else:
        flash('No se ha podido guardar', category='error')
    return redirect('/')   
