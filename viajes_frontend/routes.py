from flask import Blueprint, json, render_template, request, redirect, url_for, flash, session
import requests

routes = Blueprint('routes', __name__)

URL = "http://localhost:8000/myapp/"

@routes.route('/')
def home():
    r = requests.get(URL + "ciudades")
    data = r.json().get('data')
    return render_template('index.html', cities=data)

@routes.route('/findRoute', methods=['POST'])
def findRoute():
    return render_template('findRoute.html')

@routes.route('/city/save', methods=['POST'])
def saveCity():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"motivo": form["motivo"],
                "observaciones": form["observaciones"],
                "motivo": form["motivo"],
                "turnoId": int(form["turnoId"])}
    r = requests.post(URL + 'ciudades/save', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha guardado correctamente', category='info')
    else:
        flash('No se ha podido guardar', category='error')
    return redirect('/city/add') 

@routes.route('/road/save', methods=['POST'])
def saveRoad():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"ciudadOrigen": form["ciudadOrigen"],
                "ciudadDestino": form["ciudadDestino"],
                "distancia": form["distancia"]}
    r = requests.post(URL + 'rutas/save', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha guardado correctamente', category='info')
    else:
        flash('No se ha podido guardar', category='error')
    return redirect('/road/add')   
