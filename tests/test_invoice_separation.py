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
login3 = f'user-{uuid4()}'

creds1 = Credentials(login=login1, password='test')
creds2 = Credentials(login=login2, password='test')
creds3 = Credentials(login=login3, password='test')

r = api.user_register_post(body=creds1)
token1 = api.user_login_post(body=creds1)

r = api.user_register_post(body=creds2)
token2 = api.user_login_post(body=creds2)

r = api.user_register_post(body=creds3)
token3 = api.user_login_post(body=creds3)

req = InvoiceSeparationRequest(invoice=100, users=[login2, login3])

api_client1 = ApiClient(configuration=configuration)
api_client1.set_default_header('X-Api-Key', token1)
api1 = DefaultApi(api_client=api_client1)

api_client2 = ApiClient(configuration=configuration)
api_client2.set_default_header('X-Api-Key', token2)
api2 = DefaultApi(api_client=api_client2)

api_client3 = ApiClient(configuration=configuration)
api_client3.set_default_header('X-Api-Key', token3)
api3 = DefaultApi(api_client=api_client3)

api1.invoice_separation_post(body=req)

debt = api2.debt_get(login1);
assert debt == 50

req = UsernameRequest(username=login1)
api2.debt_post(body=req)

debt = api2.debt_get(login1);
assert debt == 0

debt = api3.debt_get(login1);
assert debt == 50
