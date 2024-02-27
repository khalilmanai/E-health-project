<?php

namespace App\Entity;

use App\Repository\LivreurRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: LivreurRepository::class)]
class Livreur extends User
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]

    public function __construct()
    {
    }
}
