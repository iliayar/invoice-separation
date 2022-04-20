from swagger_client.api.default_api import DefaultApi
from swagger_client.configuration import Configuration
from swagger_client import ApiClient

from swagger_client.models import *

from uuid import uuid4

configuration = Configuration()
configuration.host = 'http://localhost:8080'
api_client = ApiClient(configuration=configuration)
api = DefaultApi(api_client=api_client)

login1 = f'user-{uuid4()}'
login2 = f'user-{uuid4()}'

creds1 = Credentials(login=login1, password='test')
creds2 = Credentials(login=login2, password='test')

r = api.user_register_post(body=creds1)
token1 = api.user_login_post(body=creds1)

r = api.user_register_post(body=creds2)
token2 = api.user_login_post(body=creds2)

req = InvoiceSeparationRequest(invoice=100, users=[login1, login2])

api.invoice_separation_post(body=req)
