FROM microsoft/aspnetcore:2.0 AS base
WORKDIR /app
EXPOSE 80

FROM microsoft/aspnetcore-build:2.0 AS build
WORKDIR /src
COPY Dell/Dell.csproj Dell/
RUN dotnet restore Dell/Dell.csproj
COPY . .
WORKDIR /src/Dell
RUN dotnet build Dell.csproj -c Release -o /app

FROM build AS publish
RUN dotnet publish Dell.csproj -c Release -o /app

FROM base AS final
WORKDIR /app
COPY --from=publish /app .
ENTRYPOINT ["dotnet", "Dell.dll"]
