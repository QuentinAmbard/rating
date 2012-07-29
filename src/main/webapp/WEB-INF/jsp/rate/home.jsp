<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="span12 rateHome">
  <h2>Gestion des vos entreprises</h2>
  <p>Choisissez une action parmis les trois possibilités ci-dessous.</p>
  <ul class="thumbnails">
    <li class="span3">
      <div class="thumbnail">
        <a href="<c:url value="./add"/>"><img src="<c:url value="/resources/img/rate/edit.png"/>" alt=""></a>
        <div class="caption">
          <h5>Enregistrer une nouvelle entreprise</h5>
          <p class="desc">Choisissez ce menu pour créer une nouvelle entreprise. Vous pouvez à tout moment sauvegarder la progression et revenir en arrière pour éditer n'importe quelle option.</p>
          <p><a href="<c:url value="./add"/>" class="btn btn-primary">Créer une entreprise</a></p>
        </div>
      </div>
    </li>
    <li class="span3">
      <div class="thumbnail">
        <a href="<c:url value="./view"/>"><img src="<c:url value="/resources/img/rate/list.png"/>" alt=""></a>
        <div class="caption"">
          <h5>Entreprises déjà saisie</h5>
          <p class="desc">Utilisez ce menu pour visualiser la notation des entreprises déjà saisie, ou pour poursuivre/modifier les valeurs d'une entreprise déjà crée.</p>
          <p><a href="<c:url value="./view"/>" class="btn btn-primary">Voir vos entreprises</a></p>
        </div>
      </div>
    </li>
    <li class="span3">
      <div class="thumbnail">
        <a href="#"><img src="<c:url value="/resources/img/rate/stat.png"/>" alt=""></a>
        <div class="caption">
          <h5>Statistiques</h5>
          <p class="desc">Ce menu vous permet d'effectuer des comparaisons entre différents paramètres et différentes entreprises.</p>
          <p><a href="#" class="btn btn-primary">Afficher les statistiques</a></p>
        </div>
      </div>
    </li>
  </ul>
</div>
    
<%@ include file="/WEB-INF/jsp/footer.jsp" %>