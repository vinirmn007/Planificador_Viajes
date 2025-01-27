from flask import Blueprint, json, render_template, request, redirect, url_for, flash, session
import requests

routes = Blueprint('routes', __name__)

URL = "http://localhost:8000/myapp/"

@routes.route('/')
def home():
    return render_template('index.html')

@routes.route('/addCity')
def addCity():
    return render_template('addCity.html')

@routes.route('/addRoad')
def addRoad():
    return render_template('addRoad.html')

@routes.route('/findRoute', methods=['POST'])
def findRoute():
    return render_template('findRoute.html')
