# JGDriveIdApplication
<b><i>|-| JGDriveId |-| must open any kind of given google drive links.</i></b>


<b>Step 1:</b> Shkruaj funksioni JDriveId(String url) qe merr si parmeter url-ne dhe jep si rezultat vetem id-ne p.sh. gid="11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";

<b>Step 2:</b> Shkruaj funksioni jGSource(String gid) i cili lexon kodin 'MarkUP Language' të gid-së për URL-ne e pranuar pa e ekzekutuar atë dhe e kthen si rezulatat jgsource;

<b>Step 3:</b> Shkruaj funksioni locHeader(String pagesource) i cili çdo linjë terminuese e vendos në elemente të vektorit që e kthen si rezultat gVektori;

<b>Step 4:</b> Shkruaj funksioni jGConfirmCode(String jgsource) i cili kerkon per tagun 'a' atributin 'id' dhe vleren 'uc-download-link' pastaj ne te njejtin tag 'a' kerkon per atributin href ne vleren e tij kerkon per "&amp;confirm=" dhe merr 4 karakteret e ardhshme. Kthen si rezultat jgcode="6oCD";

<b>Step 5:</b> Shkruaj funksioni getJLink(String gurl, String gid) i cili bene request ne URL-ne jurl e perpunuar jgurl=gurl+"&id="+gid+"&confirm="+jgcode dhe ruan adresen përcjellëse(redirect). Kthen rezultatin jgurl;

<b>Step 6:</b> Shkruaj funksioni jgDrive(String link) i cili shfrytezon metodat getJDriveId, getConfirmCode, getLink, locHeader dhe jep si rezultat permanent linkun e gjeneruar per videon e kerkuar. Kthen si rezulatat gcache;
