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
					<xsl:for-each select="siteDatabase/site">
						<li>
							<xsl:value-of select="@name"></xsl:value-of>
							<xsl:text> - </xsl:text>
							<xsl:value-of select="@latitude"></xsl:value-of>
							<xsl:text> - </xsl:text>
							<xsl:value-of select="@longitude"></xsl:value-of>

							<ul>
								<xsl:for-each select="tower">
									<li>
										<xsl:value-of select="@name"></xsl:value-of>
										<xsl:text> - </xsl:text>
										<xsl:value-of select="@height"></xsl:value-of>
										<xsl:text> - </xsl:text>
										<xsl:value-of select="@sides"></xsl:value-of>
										<ul>
											<xsl:for-each select="equipment">
												<li>
													<xsl:value-of select="@name"></xsl:value-of>
													<xsl:text> - </xsl:text>
													<xsl:value-of select="@brand"></xsl:value-of>
													<xsl:text> - </xsl:text>
													<xsl:value-of select="@description"></xsl:value-of>
													<xsl:text> - </xsl:text>
													<xsl:value-of select="@price"></xsl:value-of>
												</li>
											</xsl:for-each>
										</ul>

									</li>
								</xsl:for-each>
							</ul>
						</li>
					</xsl:for-each>
				</ol>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>