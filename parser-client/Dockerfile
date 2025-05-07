FROM node:22.14-alpine AS builder
WORKDIR /app
COPY ./package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 4200
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0"]
