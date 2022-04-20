from swagger_client.api.default_api import DefaultApi
from swagger_client.configuration import Configuration
from swagger_client import ApiClient

from swagger_client.models import *

configuration = Configuration()
configuration.host = 'http://localhost:8080'
api_client = ApiClient(configuration=configuration)
api = DefaultApi(api_client=api_client)

creds = Credentials(login='test', password='test')

r = api.user_register_post(body=creds)
token = api.user_login_post(body=creds)

print(token)
