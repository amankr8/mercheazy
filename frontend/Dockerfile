FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
RUN npm install -g @angular/cli
COPY . .
RUN ng build --configuration=production

FROM nginx:latest
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html
EXPOSE 80
