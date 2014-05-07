<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
			</head>
			<body>
				<ol>
					<xsl:for-each select="siteDatabase/site/tower/equipment">
						<li>
							<xsl:value-of select="@name"></xsl:value-of>
							<br/>
							<xsl:text>  Site/Tower : </xsl:text>
							<xsl:value-of select="../../@name"></xsl:value-of>
							<xsl:text> / </xsl:text>
							<xsl:value-of select="../@name"></xsl:value-of>
												
						</li>
					</xsl:for-each>
				</ol>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>